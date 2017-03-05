package homework.lesson7.part1.v1.pageobjects.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

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
            results += "{\\\"completed\\\":" + task.isCompleted + ", \\\"title\\\":\\\"" + task.text + "\\\"} , ";
        }
        if (tasks.length > 0) {
            results = results.substring(0, (results.length() - 2));
        }
        results = results + "]  \" )";

        System.out.println(results);
        return results;
    }

    public void givenActive(String... taskTexts) {
        given(getTasks(false, taskTexts));
    }

    public void givenCompleted(String... taskTexts) {
        given(getTasks(true, taskTexts));
    }

    public Task[] getTasks(boolean isCompleted, String... taskTexts) {
        Task[] tasks = new Task[taskTexts.length];

        for (int i = 0; i < taskTexts.length; i++) {
            tasks[i] = new Task(taskTexts[i], isCompleted);
        }
        return tasks;
    }

    public void given(Task... tasks) {
        if (!url().equals("https://todomvc4tasj.herokuapp.com/")){
            open("https://todomvc4tasj.herokuapp.com/");
        }
        String jsCommand = makeGivenCommand(tasks);
        executeJavaScript(jsCommand);
        refresh();
    }

    public static class Task {
        String text;
        boolean isCompleted;

//   Вопрос. Зачем нужна была данная реализация?
//
//        public static Task activeTask(String text) {
//            return new Task(text, false);
//        }
//
//        public static Task completedTask(String text) {
//            return new Task(text, true);
//        }

        public Task(String text, boolean isCompleted) {
            this.text = text;
            this.isCompleted = isCompleted;
        }
    }

    public enum TaskStatus {
        ACTIVE("false"),
        COMPLETED("true");

        public String status;

        TaskStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return status;
        }
    }
}
