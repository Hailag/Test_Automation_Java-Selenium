package homework.lesson7.part1.v1.pagemodules.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class TodoMVC {

    public static ElementsCollection tasks = $$("#todo-list>li");

    @Step
    public static void assertNoTasks() {
        tasks.filter(visible).shouldBe(empty);
    }

    @Step
    public static void toggleAll() {
        $("#toggle-all").click();
    }

    @Step
    public static void clearCompleted() {
        $("#clear-completed").click();
    }

    @Step
    public static void assertTasks(String... taskTexts) {
        tasks.filterBy(visible).shouldHave(exactTexts(taskTexts));
    }

    @Step
    public static void toggle(String taskText) {
        tasks.find(exactText(taskText)).$(".toggle").click();
    }

    @Step
    public static void add(String... taskTexts) {
        for (String text : taskTexts) {
            $("#new-todo").setValue(text).pressEnter();
        }
    }

    @Step
    public static SelenideElement startEdit(String oldTaskText, String newTaskText) {
        tasks.findBy(exactText(oldTaskText)).doubleClick();
        return tasks.findBy(cssClass("editing")).$(".edit").setValue(newTaskText);
    }

    @Step
    public static void edit(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText, newTaskText).pressEnter();
    }

    @Step
    public static void cancelEdit(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText, newTaskText).pressEscape();
    }

    @Step
    public static void editByTab(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText, newTaskText).pressTab();
    }

    @Step
    public static void editByClickOutOfTask(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText, newTaskText);
        $("#header h1").click();
    }

    @Step
    public static void delete(String taskText) {
        tasks.find(exactText(taskText)).hover().$(".destroy").click();
    }

    @Step
    public static void filterActive() {
        $(By.linkText("Active")).click();
    }

    @Step
    public static void filterCompleted() {
        $(By.linkText("Completed")).click();
    }

    @Step
    public static void filterAll() {
        $(By.linkText("All")).click();
    }

    @Step
    public static void assertItemsLeft(int itemsLeftCount) {
        $("#todo-count>strong").shouldHave(exactText(Integer.toString(itemsLeftCount)));
    }

    public static String makeGivenCommand(Task... tasks) {
        String results = "localStorage.setItem(\"todos-troopjs\", \"[";

        for (Task task : tasks) {
            results += task + ", ";
        }
        if (tasks.length > 0) {
            results = results.substring(0, (results.length() - 2));
        }
        results = results + "]  \" )";

        System.out.println(results);
        return results;
    }

    public static Task[] getTasks(TaskType taskType, String... taskTexts) {
        Task[] tasks = new Task[taskTexts.length];

        for (int i = 0; i < taskTexts.length; i++) {
            tasks[i] = aTask(taskType, taskTexts[i]);
        }
        return tasks;
    }

    public static void given(Task... tasks) {
        if (!url().equals("https://todomvc4tasj.herokuapp.com/")) {
            open("https://todomvc4tasj.herokuapp.com/");
        }
        String jsCommand = makeGivenCommand(tasks);
        executeJavaScript(jsCommand);
        refresh();
    }

    public static class Task {
        String text;
        TaskType type;

        @Override
        public String toString() {
            return "{\\\"completed\\\":" + this.type + ", \\\"title\\\":\\\"" + this.text + "\\\"}";
        }


        public Task(TaskType type, String text) {
            this.text = text;
            this.type = type;
        }
    }

    public static enum TaskType {
        ACTIVE("false"),
        COMPLETED("true");

        public String type;

        TaskType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return type;
        }
    }

    public static void givenAtActive(TaskType taskType, String... taskTexts) {
        given(getTasks(taskType, taskTexts));
        filterActive();
    }

    public static void givenAtActive(Task... tasks) {
        given(tasks);
        filterActive();
    }

    public static void givenAtCompleted(TaskType taskType, String... taskTexts) {
        given(getTasks(taskType, taskTexts));
        filterCompleted();
    }

    public static void givenAtCompleted(Task... tasks) {
        given(tasks);
        filterCompleted();
    }

    public static void givenAtAll(TaskType taskType, String... taskTexts) {
        given(getTasks(taskType, taskTexts));
        filterAll();
    }

    public static void givenAtAll(Task... tasks) {
        given(tasks);
        filterAll();
    }

    public static Task aTask(TaskType type, String text) {
        return new Task(type, text);
    }

}

