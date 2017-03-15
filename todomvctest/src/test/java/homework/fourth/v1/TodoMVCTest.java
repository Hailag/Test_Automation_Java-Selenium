package homework.fourth.v1;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Test;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;
import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.WebDriverRunner.url;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static homework.fourth.v1.TodoMVCTest.TaskType.ACTIVE;
import static homework.fourth.v1.TodoMVCTest.TaskType.COMPLETED;


public class TodoMVCTest extends AtTodoMVCPageWithClearedDataAfterEachTest {

    @Test
    public void testTasksLifeCycle() {

        add("1");
        toggle("1");
        assertTasks("1");

        filterActive();
        assertNoTasks();
        add("2");
        toggleAll();
        assertNoTasks();

        filterCompleted();
        assertTasks("1", "2");
        toggle("2");
        assertTasks("1");
        clearCompleted();
        assertNoTasks();

        filterAll();
        assertTasks("2");
    }

    @Test
    public void testEditAtAll() {
        givenAtAll(ACTIVE,"1", "2");
        edit("2", "2 edited");
        assertTasks("1", "2 edited");
        assertItemsLeft(2);
    }

    @Test
    public void testCancelEditAtAll() {
        givenAtAll(ACTIVE,"1");
        cancelEdit("1", "edited");
        assertTasks("1");
        assertItemsLeft(1);
    }

    @Test
    public void testDeleteAtAll() {
        givenAtAll(ACTIVE,"1");

        delete("1");
        assertNoTasks();
    }
    @Test
    public void testAllCompleteAtAll() {
        givenAtAll(ACTIVE,"1", "2");
        toggleAll();
        assertItemsLeft(0);
    }

    @Test
    public void testClearCompleteAtAll() {
        givenAtAll(ACTIVE,"1", "2");
        toggleAll();
        clearCompleted();
        assertNoTasks();
    }

    @Test
    public void testReopenAtAll() {
        givenAtAll(COMPLETED,"1");
        toggle("1");
        assertTasks("1");
        assertItemsLeft(1);
    }

    @Test
    public void testReopenAllAtAll() {
        givenAtAll(COMPLETED,"1", "2");
        toggleAll();
        assertTasks("1", "2");
        assertItemsLeft(2);
    }

    @Test
    public void testCancelEditByEscAtAll() {
        givenAtAll(aTask(ACTIVE, "1"), aTask(COMPLETED, "2"));

        cancelEdit("2", "2 edited");
        assertTasks("1", "2");
        assertItemsLeft(1);
    }

    @Test
    public void editByClickOutOfTaskAtAll() {
        givenAtAll(ACTIVE, "1", "2");

        editByClickOutOfTask("2", "2 edited");
        assertTasks("1", "2 edited");
        assertItemsLeft(2);
    }

    @Test
    public void testSwitchFromAllToCompleted() {
        givenAtAll(aTask(ACTIVE, "1"), aTask(COMPLETED, "2"));

        filterCompleted();
        assertTasks( "2");
        assertItemsLeft(1);
    }

    @Test
    public void testEditAtActive() {
        givenAtActive(ACTIVE,"1", "2");

        edit("2", "2 edited");
        assertTasks("1", "2 edited");
        assertItemsLeft(2);
    }

    @Test
    public void testDeleteAtActive() {
        givenAtActive(ACTIVE, "1", "2");

        delete("2");
        assertTasks("1");
        assertItemsLeft(1);
    }


    @Test
    public void testCompleteAtActive() {
        givenAtActive(ACTIVE,"1", "2");

        toggleAll();
        assertItemsLeft(0);
    }

    @Test
    public void testClearCompleteAtActive() {
        givenAtActive(ACTIVE, "1");

        clearCompleted();
        assertNoTasks();
    }

    @Test
    public void testReopenAllAtActive() {
        givenAtActive(COMPLETED,"1", "2");

        assertNoTasks();
        toggleAll();
        assertItemsLeft(2);
    }

    @Test
    public void testCancelEditAtActive() {
        givenAtActive(aTask(ACTIVE, "1"), aTask(COMPLETED, "2"));

        cancelEdit("1", "1 edited");
        assertTasks("1");
        assertItemsLeft(1);
    }

    @Test
    public void testEditByTABAtActive() {
        givenAtActive(aTask(ACTIVE, "1"), aTask(COMPLETED, "2"));

        editByTab("1", "1 edited");
        assertTasks("1 edited");
        assertItemsLeft(1);
    }

    @Test
    public void testSwitchFromActiveToAll() {
        givenAtActive(aTask(ACTIVE, "1"), aTask(COMPLETED, "2"));

        filterAll();
        assertTasks("1", "2");
        assertItemsLeft(1);
    }

    @Test
    public void testSwitchFromActiveToCompleted() {
        givenAtActive(aTask(ACTIVE, "1"), aTask(COMPLETED, "2"));

        filterCompleted();
        assertTasks( "2");
        assertItemsLeft(1);
    }

    @Test
    public void testDeleteAtCompleted() {
        givenAtCompleted(COMPLETED,"1", "2");

        delete("1");
        assertTasks("2");
        assertItemsLeft(0);
    }

    @Test
    public void testAddAtCompleted() {
        givenAtCompleted(COMPLETED, "1");
        add("2");
        assertTasks("1");
        assertItemsLeft(1);
    }

    @Test
    public void testEditAtCompleted() {
        givenAtCompleted(COMPLETED, "1", "2");

        edit("2", "2 edit");
        assertTasks("1", "2 edit");
        assertItemsLeft(0);
    }

    @Test
    public void testCompleteAllAtCompleted() {
        givenAtCompleted(COMPLETED, "1", "2");

        toggleAll();
        assertTasks("1","2");
        assertItemsLeft(0);
    }

    @Test
    public void testReopenAllToCompleted() {
        givenAtCompleted(COMPLETED, "1");

        toggleAll();
        assertNoTasks();
        assertItemsLeft(1);
    }

    @Test
    public void deleteByClearTextAtCompleted() {
        givenAtCompleted(COMPLETED, "1", "2");

        edit("2", "");
        assertTasks("1");
        assertItemsLeft(0);
    }

    @Test
    public void testSwitchFromCompletedToAll() {
        givenAtCompleted(aTask(ACTIVE, "1"), aTask(COMPLETED, "2"));

        filterAll();
        assertTasks( "1", "2");
        assertItemsLeft(1);
    }

    @Test
    public void testSwitchFromCompletedToActive() {
        givenAtCompleted(aTask(ACTIVE, "1"), aTask(COMPLETED, "2"));

        filterActive();
        assertTasks( "1");
        assertItemsLeft(1);
    }







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
        $("#header h1").click(); //TODO Узнать можно ли кликнуть на пустую область?
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

    @Step
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

    @Step
    public Task[] getTasks(TaskType taskType, String... taskTexts) {
        Task[] tasks = new Task[taskTexts.length];

        for (int i = 0; i < taskTexts.length; i++) {
            tasks[i] = aTask(taskType, taskTexts[i]);
        }
        return tasks;
    }

    @Step
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


    @Step
    public void givenAtActive(TaskType taskType, String... taskTexts) {
        given(getTasks(taskType, taskTexts));
        filterActive();
    }

    @Step
    public void givenAtActive(Task... tasks) {
        given(tasks);
        filterActive();
    }

    @Step
    public void givenAtCompleted(TaskType taskType, String... taskTexts) {
        given(getTasks(taskType, taskTexts));
        filterCompleted();
    }

    @Step
    public void givenAtCompleted(Task... tasks) {
        given(tasks);
        filterCompleted();
    }

    @Step
    public void givenAtAll(TaskType taskType, String... taskTexts) {
        given(getTasks(taskType, taskTexts));
        filterAll();
    }

    @Step
    public void givenAtAll(Task... tasks) {
        given(tasks);
        filterAll();
    }

    public Task aTask(TaskType type, String text) {
        return new Task(type, text);
    }
}
