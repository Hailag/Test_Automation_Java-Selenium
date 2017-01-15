package homework.third.v2;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.SelenideElement;
import com.google.common.io.Files;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
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

    @After
    public void tearDown() throws IOException {
        screenshot();
    }

    @Attachment(type = "image/png")
    public byte[] screenshot() throws IOException {
        File screenshot = Screenshots.takeScreenShotAsFile();
        return Files.toByteArray(screenshot);
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
        add("1","2");

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

}