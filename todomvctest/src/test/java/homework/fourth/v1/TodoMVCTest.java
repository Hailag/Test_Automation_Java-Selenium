package homework.fourth.v1;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import homework.third.v3.AtTodoMVCPageWithClearedDataAfterEachTest;
import org.junit.Test;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static homework.fourth.v1.TodoMVCTest.Task.activeTask;
import static homework.fourth.v1.TodoMVCTest.Task.completedTask;


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
        given(activeTask("1"), completedTask("2"));
        edit("2", "2 edited");
        assertTasks("1", "2 edited");
        assertItemsLeft(1);
    }

    @Test
    public void testCancelEditAtAll() {
        givenActive("1");
        cancelEdit("1", "edited");
        assertTasks("1");
        assertItemsLeft(1);
    }

    @Test
    public void testDeleteAtCompleted() {
        givenCompleted("1", "2");
        filterCompleted();

        delete("1");
        assertTasks("2");
        assertItemsLeft(0);
    }

    @Test
    public void testDeleteAtAll(){
        givenActive("1");
        filterAll();
        delete("1");
        assertNoTasks();
    }

    @Test
    public void testAllCompleteAtAll(){
        givenActive("1", "2");
        toggleAll();
        assertItemsLeft(0);
    }

    @Test
    public void testClearCompleteAtAll () {
        givenActive("1", "2");
        toggleAll();
        clearCompleted();
        assertNoTasks();
    }

    @Test
    public void testReopenAtAll () {
        givenCompleted("1");
        toggle("1");
        assertTasks("1");
        assertItemsLeft(1);
    }

    @Test
    public void testReopenAllAtAll (){
        givenCompleted("1", "2");
        toggleAll();
        assertTasks("1","2");
        assertItemsLeft(2);
    }

    @Test
    public void testEditAtActive (){
        givenActive("1", "2");
        filterActive();
        edit("2", "2 edited");
        assertTasks("1", "2 edited");
        assertItemsLeft(2);
    }

    @Test
    public void testDeleteAtActive(){
        givenActive("1");
        filterActive();
        delete("1");
        assertNoTasks();
        assertItemsLeft(0);
    }

    @Test
    public void testCompleteAtActive () {
        givenActive("1", "2");
        filterActive();
        toggleAll();
        assertItemsLeft(0);
    }

    @Test
    public void testClearCompleteAtActive () {
        givenActive("1");
        filterActive();
        toggle("1");
        clearCompleted();
        assertItemsLeft(0);
    }

    @Test
    public void testReopenAllAtActive () {
        givenCompleted("1", "2");
        filterActive();
        toggleAll();
        assertItemsLeft(2);
    }

    @Test
    public void testAddAtCompleted() {
        givenCompleted("1");
        filterCompleted();
        add("2");
        assertTasks("1");
        assertItemsLeft(1);
    }

    @Test
    public void testEditAtCompleted () {
        givenCompleted("1", "2");
        filterCompleted();
        edit("2", "2 edit");
        assertTasks("1", "2 edit");
        assertItemsLeft(0);
    }

    @Test
    public void testCompleteAllAtCompleted () {
        givenCompleted("1", "2");
        filterCompleted();
        clearCompleted();
        assertNoTasks();
    }

    @Test
    public void testReopenAllToCompleted () {
        givenCompleted("1");
        filterCompleted();
        toggleAll();
        assertNoTasks();
        assertItemsLeft(1);
    }







    ElementsCollection tasks = $$("#todo-list>li");


    @Step
    private void assertNoTasks() {
        tasks.filter(visible).shouldBe(empty);
    }

    @Step
    private void toggleAll() {
        $("#toggle-all").click();
    }

    @Step
    private void clearCompleted() {
        $("#clear-completed").click();
    }

    @Step
    private void assertTasks(String... taskTexts) {
        tasks.filterBy(visible).shouldHave(exactTexts(taskTexts));
    }

    @Step
    private void toggle(String taskText) {
        tasks.find(exactText(taskText)).$(".toggle").click();
    }

    @Step
    private void add(String... taskTexts) {
        for (String text : taskTexts) {
            $("#new-todo").setValue(text).pressEnter();
        }
    }

    @Step
    private SelenideElement startEdit(String oldTaskText, String newTaskText) {
        tasks.findBy(exactText(oldTaskText)).doubleClick();
        return tasks.findBy(cssClass("editing")).$(".edit").setValue(newTaskText);
    }

    @Step
    private void edit(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText, newTaskText).pressEnter();
    }

    @Step
    private void cancelEdit(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText, newTaskText).pressEscape();
    }

    @Step
    private void delete(String taskText) {
        tasks.find(exactText(taskText)).hover().$(".destroy").click();
    }

    @Step
    private void filterActive() {
        $(By.linkText("Active")).click();
    }

    @Step
    private void filterCompleted() {
        $(By.linkText("Completed")).click();
    }

    @Step
    private void filterAll() {
        $(By.linkText("All")).click();
    }

    @Step
    private void assertItemsLeft(int itemsLeftCount) {
        $("#todo-count>strong").shouldHave(exactText(Integer.toString(itemsLeftCount)));
    }

    private String makeGivenCommand(Task... tasks) {
        String results = "localStorage.setItem(\"todos-troopjs\", \"[";

        for (Task task : tasks) {
            results += "{\\\"completed\\\":"+ task.isCompleted +", \\\"title\\\":\\\"" + task.text + "\\\"} , ";
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

    public Task[] getTasks (boolean isCompleted, String... taskTexts){
        Task[] tasks = new Task[taskTexts.length];

        for (int i = 0; i <taskTexts.length; i++){
            tasks[i] = new Task(taskTexts[i], isCompleted);
        }
        return tasks;
    }

    public void given(Task... tasks) {
        String jsCommand = makeGivenCommand(tasks);
        executeJavaScript(jsCommand);
        refresh();
    }

    public static class Task {
        String text;
        boolean isCompleted;

        public static Task activeTask (String text){
            return new Task(text, false);
        }

        public static Task completedTask (String text) {
            return new Task(text, true);
        }

        public Task(String text, boolean isCompleted) {
            this.text = text;
            this.isCompleted = isCompleted;
        }
    }

}
