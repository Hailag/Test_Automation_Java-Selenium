package homework.second.v1;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.CollectionCondition.empty;


public class TodoMVCTest {
    @Test
    public void testTasksLifeCycle() {

        open("https://todomvc4tasj.herokuapp.com/");

        add("1");
        edit("1", "1 edited");
        toggle("1 edited");
        assertTasks("1 edited");


        filterActive();
        assertNoTasks();
        add("2");
        cancelEdit("2", "2 edited");
        assertTasks("2");
        toggleAll();
        assertNoTasks();


        filterCompleted();
        assertTasks("1 edited","2");
        toggle("2");
        assertTasks("1 edited");
        clearCompleted();
        assertNoTasks();


        filterAll();
        assertTasks("2");
        delete("2");
        assertNoTasks();

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

    private void filterCompleted () {
        $(By.linkText("Completed")).click();
    }

    private void filterAll () {
        $(By.linkText("All")).click();
    }

}
