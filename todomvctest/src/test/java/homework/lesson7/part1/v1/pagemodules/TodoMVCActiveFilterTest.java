package homework.lesson7.part1.v1.pagemodules;

import org.junit.Test;

import static homework.lesson7.part1.v1.pagemodules.pages.TodoMVCPage.TaskType.*;
import static homework.lesson7.part1.v1.pagemodules.pages.TodoMVCPage.*;


public class TodoMVCActiveFilterTest {
    
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
    public void testClearCompleteAtActive() { //Исправить
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

}

