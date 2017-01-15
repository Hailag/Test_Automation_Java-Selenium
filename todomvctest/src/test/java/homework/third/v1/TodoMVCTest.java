package homework.third.v1;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TodoMVCTest {
    @Before
    public void openPage() {
        open("https://todomvc4tasj.herokuapp.com/");
    }

    @After
    public void clearData() {
        executeJavaScript("localStorage.clear()");
    }

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
        //given
        add("1", "2");

        edit("2", "2 edited");
        assertTasks("1", "2 edited");
        assertItemsLeft(2);
    }

    @Test
    public void testCancelEditAtAll() {
        //given
        add("1");

        cancelEdit("1", "edited");
        assertTasks("1");
        assertItemsLeft(1);
    }

    @Test
    public void testDeleteAtCompleted() {
        //given - completed tasks
        add("1", "2");
        toggleAll();
        filterCompleted();

        delete("1");
        assertTasks("2");
        assertItemsLeft(0);
    }


    ElementsCollection tasks = $$("#todo-list>li");

    private void assertNoTasks() {
        tasks.filter(visible).shouldBe(empty);
    }

    private void toggleAll() {
        $("#toggle-all").click();
    }

    private void clearCompleted() {
        $("#clear-completed").click();
    }

    private void assertTasks(String... taskTexts) {
        tasks.filterBy(visible).shouldHave(exactTexts(taskTexts));
    }

    private void toggle(String taskText) {
        tasks.find(exactText(taskText)).$(".toggle").click();
    }

    private void add(String... taskTexts) {
        for (String text : taskTexts) {
            $("#new-todo").setValue(text).pressEnter();
        }
    }

    private SelenideElement startEdit(String oldTaskText, String newTaskText) {
        tasks.findBy(exactText(oldTaskText)).doubleClick();
        return tasks.findBy(cssClass("editing")).$(".edit").setValue(newTaskText);
    }

    private void edit(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText, newTaskText).pressEnter();
    }

    private void cancelEdit(String oldTaskText, String newTaskText) {
        startEdit(oldTaskText, newTaskText).pressEscape();
    }

    private void delete(String taskText) {
        tasks.find(exactText(taskText)).hover().$(".destroy").click();
    }

    private void filterActive() {
        $(By.linkText("Active")).click();
    }

    private void filterCompleted() {
        $(By.linkText("Completed")).click();
    }

    private void filterAll() {
        $(By.linkText("All")).click();
    }

    private void assertItemsLeft(int itemsLeftCount) {
        $("#todo-count>strong").shouldHave(exactText(Integer.toString(itemsLeftCount)));
    }

}