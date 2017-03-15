package homework.lesson7.part1.v1.pagemodules;

import homework.lesson7.part1.v1.pagemodules.testconfigs.AtTodoMVCPageWithClearedDataAfterEachTest;
import org.junit.Test;
import static homework.lesson7.part1.v1.pagemodules.pages.TodoMVCPage.*;

public class TodoMVCEndToEndTest extends AtTodoMVCPageWithClearedDataAfterEachTest {

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

}
