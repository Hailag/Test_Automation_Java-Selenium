package homework.lesson7.part1.v1.pagemodules;

import org.junit.Test;

import static homework.lesson7.part1.v1.pagemodules.pages.TodoMVCPage.*;
import static homework.lesson7.part1.v1.pagemodules.pages.TodoMVCPage.TaskType.*;


public class TodoMVCActiveFilterTest {

    @Test
    public void testEdit() {
        givenAtActive(ACTIVE, "1");

        edit("1", "1 edited");
        assertTasks( "1 edited");
        assertItemsLeft(1);
    }

    @Test
    public void testDelete() {
        givenAtActive(ACTIVE, "1");

        delete("1");
        assertNoTasks();
    }


    @Test
    public void testComplete() {
        givenAtActive(ACTIVE,"1", "2");

        toggle("1");
        assertTasks("2");
        assertItemsLeft(1);

    }

    @Test
    public void testClearCompleted() {
        givenAtActive(aTask(ACTIVE, "1"), aTask(COMPLETED, "2"), aTask(COMPLETED, "3"));

        clearCompleted();
        assertTasks("1");
        assertItemsLeft(1);
    }


    @Test
    public void testCancelEdit() {
        givenAtActive(aTask(ACTIVE, "1"), aTask(COMPLETED, "2"));

        cancelEdit("1", "1 edited");
        assertTasks("1");
        assertItemsLeft(1);
    }

    @Test
    public void testEditByTAB() {
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
        assertItemsLeft(2);
    }

}

