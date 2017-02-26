package homework.lesson7.part1.v1.pagemodules.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Tasks {

    public static  ElementsCollection tasks = $$("#todo-list>li");

    @Step
    public static  void assertNoTasks() {
        tasks.filter(visible).shouldBe(empty);
    }

    @Step
    public static  void toggleAll() {
        $("#toggle-all").click();
    }

    @Step
    public static  void clearCompleted() {
        $("#clear-completed").click();
    }

    @Step
    public static  void assertTasks(String... taskTexts) {
        tasks.filterBy(visible).shouldHave(exactTexts(taskTexts));
    }

    @Step
    public static  void toggle(String taskText) {
        tasks.find(exactText(taskText)).$(".toggle").click();
    }

    @Step
    public static  void add(String... taskTexts) {
        for (String text : taskTexts) {
            $("#new-todo").setValue(text).pressEnter();
        }
    }

    @Step
    public static  SelenideElement startEdit(String oldTaskText, String newTaskText) {
        tasks.findBy(exactText(oldTaskText)).doubleClick();
        return tasks.findBy(cssClass("editing")).$(".edit").setValue(newTaskText);
    }

    @Step
    public static  void edit(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText, newTaskText).pressEnter();
    }

    @Step
    public static  void cancelEdit(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText, newTaskText).pressEscape();
    }

    @Step
    public static  void delete(String taskText) {
        tasks.find(exactText(taskText)).hover().$(".destroy").click();
    }

    @Step
    public static  void filterActive() {
        $(By.linkText("Active")).click();
    }

    @Step
    public static  void filterCompleted() {
        $(By.linkText("Completed")).click();
    }

    @Step
    public static  void filterAll() {
        $(By.linkText("All")).click();
    }

    @Step
    public static  void assertItemsLeft(int itemsLeftCount) {
        $("#todo-count>strong").shouldHave(exactText(Integer.toString(itemsLeftCount)));
    }

    public static  String makeGivenCommand(Task... tasks) {
        String results = "localStorage.setItem(\"todos-troopjs\", \"[";

        for (Task task : tasks) {
            results += "{\\\"completed\\\":" + task.isCompleted + ", \\\"title\\\":\\\"" + task.text + "\\\"} , ";
        }
        if (tasks.length > 0) {
            results = results.substring(0, (results.length() - 2));
        }
        results = results + "]  \" )";

        System.out.println(results);
        return results;
    }

    public static  void givenActive(String... taskTexts) {
        given(getTasks(false, taskTexts));

    }

    public static  void givenCompleted(String... taskTexts) {

        given(getTasks(true, taskTexts));

    }

    public static  Task[] getTasks(boolean isCompleted, String... taskTexts) {
        Task[] tasks = new Task[taskTexts.length];

        for (int i = 0; i < taskTexts.length; i++) {
            tasks[i] = new Task(taskTexts[i], isCompleted);
        }
        return tasks;
    }

    public static  void given(Task... tasks) {
        String jsCommand = makeGivenCommand(tasks);
        executeJavaScript(jsCommand);
        refresh();
    }

    public static class Task {
        String text;
        boolean isCompleted;

        public static Task activeTask(String text) {
            return new Task(text, false);
        }

        public static Task completedTask(String text) {
            return new Task(text, true);
        }

        public Task(String text, boolean isCompleted) {
            this.text = text;
            this.isCompleted = isCompleted;
        }
    }
}
