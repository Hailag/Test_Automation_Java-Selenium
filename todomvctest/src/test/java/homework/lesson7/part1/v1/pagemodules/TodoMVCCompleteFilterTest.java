package homework.lesson7.part1.v1.pagemodules;

import org.junit.Test;

import static homework.lesson7.part1.v1.pagemodules.pages.TodoMVCPage.*;
import static homework.lesson7.part1.v1.pagemodules.pages.TodoMVCPage.TaskType.*;

public class TodoMVCCompleteFilterTest {

    @Test
    public void testDelete() {
        givenAtCompleted(COMPLETED,"1", "2");

        delete("1");
        assertTasks("2");
        assertItemsLeft(0);
    }

    @Test
    public void testEdit() {
        givenAtCompleted(COMPLETED, "1", "2");

        edit("2", "2 edit");
        assertTasks("1", "2 edit");
        assertItemsLeft(0);
    }


    @Test
    public void testReopenAll() {
        givenAtCompleted(COMPLETED, "1");

        toggleAll();
        assertNoTasks();
        assertItemsLeft(1);
    }

    @Test
    public void testDeleteByClear() {
        givenAtCompleted(COMPLETED, "1", "2");

        edit("2", "");
        assertTasks("1");
        assertItemsLeft(0);
    }


    @Test
    public void testSwitchFromCompletedToActive() {
        givenAtCompleted(aTask(ACTIVE, "1"), aTask(COMPLETED, "2"));

        filterActive();
        assertTasks( "1");
        assertItemsLeft(1);
    }
}
