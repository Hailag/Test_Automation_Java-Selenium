package homework.lesson7.part1.v1.pageobjects.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.*;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class TodoMVCPage {

    public ElementsCollection tasks = $$("#todo-list>li");

    @Step
    public void assertNoTasks() {
        tasks.filter(visible).shouldBe(empty);
    }

    @Step
    public void toggleAll() {
        $("#toggle-all").click();
    }

    @Step
    public void clearCompleted() {
        $("#clear-completed").click();
    }

    @Step
    public void assertTasks(String... taskTexts) {
        tasks.filterBy(visible).shouldHave(exactTexts(taskTexts));
    }

    @Step
    public void toggle(String taskText) {
        tasks.find(exactText(taskText)).$(".toggle").click();
    }

    @Step
    public void add(String... taskTexts) {
        for (String text : taskTexts) {
            $("#new-todo").setValue(text).pressEnter();
        }
    }

    @Step
    public SelenideElement startEdit(String oldTaskText, String newTaskText) {
        tasks.findBy(exactText(oldTaskText)).doubleClick();
        return tasks.findBy(cssClass("editing")).$(".edit").setValue(newTaskText);
    }

    @Step
    public void edit(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText, newTaskText).pressEnter();
    }

    @Step
    public void cancelEdit(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText, newTaskText).pressEscape();
    }

    @Step
    public void editByTab(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText, newTaskText).pressTab();
    }

    @Step
    public void editByClickOutOfTask(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText, newTaskText);
        $("#header h1").click(); // Можно ли кликнуть на пустое поле?
    }

    @Step
    public void delete(String taskText) {
        tasks.find(exactText(taskText)).hover().$(".destroy").click();
    }

    @Step
    public void filterActive() {
        $(By.linkText("Active")).click();
    }

    @Step
    public void filterCompleted() {
        $(By.linkText("Completed")).click();
    }

    @Step
    public void filterAll() {
        $(By.linkText("All")).click();
    }

    @Step
    public void assertItemsLeft(int itemsLeftCount) {
        $("#todo-count>strong").shouldHave(exactText(Integer.toString(itemsLeftCount)));
    }

    public String makeGivenCommand(Task... tasks) {
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


    public Task[] getTasks(TaskType taskType, String... taskTexts) {
        Task[] tasks = new Task[taskTexts.length];

        for (int i = 0; i < taskTexts.length; i++) {
            tasks[i] = aTask(taskType, taskTexts[i]);
        }
        return tasks;
    }

    public void given(Task... tasks) {
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

    public enum TaskType {
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


    public void givenAtActive(TaskType taskType, String... taskTexts) {
        given(getTasks(taskType, taskTexts));
        filterActive();
    }

    public void givenAtActive(Task... tasks) { // используем в случае если на надо создать 2 таски (1 активная, 2 закомпличенная)
        given(tasks);
        filterActive();
    }

    public void givenAtCompleted(TaskType taskType, String... taskTexts) {
        given(getTasks(taskType, taskTexts));
        filterCompleted();
    }

    public void givenAtCompleted(Task... tasks) {
        given(tasks);
        filterCompleted();
    }

    public void givenAtAll(TaskType taskType, String... taskTexts) {
        given(getTasks(taskType, taskTexts));
        filterAll();
    }

    public void givenAtAll(Task... tasks) {
        given(tasks);
        filterAll();
    }

    public Task aTask(TaskType type, String text) {
        return new Task(type, text);
    }

}

