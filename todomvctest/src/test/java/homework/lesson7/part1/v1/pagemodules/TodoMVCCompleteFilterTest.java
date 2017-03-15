package homework.lesson7.part1.v1.pagemodules;

import org.junit.Test;

import static homework.lesson7.part1.v1.pagemodules.pages.TodoMVCPage.TaskType.*;
import static homework.lesson7.part1.v1.pagemodules.pages.TodoMVCPage.*;


public class TodoMVCCompleteFilterTest {

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
}
