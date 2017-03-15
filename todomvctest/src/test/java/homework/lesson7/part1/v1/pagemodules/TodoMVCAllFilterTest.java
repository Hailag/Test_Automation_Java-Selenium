package homework.lesson7.part1.v1.pagemodules;

import org.junit.Test;

import static homework.lesson7.part1.v1.pagemodules.pages.TodoMVCPage.*;
import static homework.lesson7.part1.v1.pagemodules.pages.TodoMVCPage.TaskType.*;


public class TodoMVCAllFilterTest {

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

}
