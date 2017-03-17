package homework.lesson7.part1.v1.pagemodules;

import org.junit.Test;

import static homework.lesson7.part1.v1.pagemodules.pages.TodoMVCPage.*;
import static homework.lesson7.part1.v1.pagemodules.pages.TodoMVCPage.TaskType.*;


public class TodoMVCAllFilterTest {

    @Test
    public void testEdit() {
        givenAtAll(aTask(ACTIVE, "1"), aTask(COMPLETED, "2"));

        edit("2", "2 edited");
        assertTasks("1", "2 edited");
        assertItemsLeft(1);
    }

    @Test
    public void testCancelEdit() {
        givenAtAll(ACTIVE, "1");

        cancelEdit("1", "edited");
        assertTasks("1");
        assertItemsLeft(1);
    }

    @Test
    public void testDelete() {
        givenAtAll(aTask(ACTIVE, "1"), aTask(COMPLETED, "2"));

        delete("1");
        assertTasks("2");
        assertItemsLeft(0);
    }
    @Test
    public void testCompleteAll() {
        givenAtAll(ACTIVE,"1", "2");

        toggleAll();
        assertTasks("1", "2");
        assertItemsLeft(0);
    }

    @Test
    public void testClearCompleted() {
        givenAtAll(COMPLETED, "1","2");

        clearCompleted();
        assertNoTasks();

    }

    @Test
    public void testReopen() {
        givenAtAll(COMPLETED,"1");

        toggle("1");
        assertTasks("1");
        assertItemsLeft(1);
    }


    @Test
    public void testCancelEditByEsc() {
        givenAtAll(aTask(ACTIVE, "1"), aTask(COMPLETED, "2"));

        cancelEdit("2", "2 edited");
        assertTasks("1", "2");
        assertItemsLeft(1);
    }

    @Test
    public void testEditByClickOutOfTask() {
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
