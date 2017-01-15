package ua.net.itlabs;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;

import com.codeborne.selenide.ElementsCollection;
import org.junit.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;


public class TodoMVCTest {

    @Test
    public void testTasksLifeCycle() {

        open("https://todomvc4tasj.herokuapp.com/");

        add("1", "2", "3", "4");
        assertTasksAre("1", "2", "3", "4");

        delete("2");
        assertTasksAre("1", "3", "4");

        toggle("4");
        clearCompleted();
        assertTasksAre("1", "3");

        toggleAll();
        clearCompleted();
        assertTasksEmpty();

    }

    ElementsCollection tasks = $$("#todo-list>li");

    private void assertTasksEmpty() {
        tasks.shouldBe(empty);
    }

    private void toggleAll() {
        $("#toggle-all").click();
    }

    private void clearCompleted() {
        $("#clear-completed").click();
    }

    private void assertTasksAre(String... taskTexts) {
        tasks.shouldHave(exactTexts(taskTexts));
    }

    private void toggle(String taskText) {
        tasks.find(exactText(taskText)).$(".toggle").click();
    }

    private void add(String... taskTexts) {
        for (String text : taskTexts) {
            $("#new-todo").setValue(text).pressEnter();
        }
    }

    private void delete(String taskText) {
        tasks.find(exactText(taskText)).hover().$(".destroy").click();
    }
}
