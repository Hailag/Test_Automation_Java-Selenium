package homework.lesson7.part1.v1.pagemodules;

import org.junit.Test;

import static homework.lesson7.part1.v1.pagemodules.pages.TodoMVCPage.TaskType.*;
import static homework.lesson7.part1.v1.pagemodules.pages.TodoMVCPage.*;


public class TodoMVCEndToEndTest {

    @Test
    public void TodoMVSGeneralTest() {

        givenAtAll(ACTIVE, "1");
        toggle("1");
        assertTasks("1");

        filterActive();
        assertNoTasks();
        givenAtActive(aTask(COMPLETED, "1"), aTask(ACTIVE, "2"));
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

}
