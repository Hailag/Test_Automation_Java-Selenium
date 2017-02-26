package homework.lesson7.part1.v1.pagemodules;

import org.junit.Test;
import static homework.lesson7.part1.v1.pagemodules.pages.Tasks.*;

public class TodoMVCCompleteFilterTest {
   
    @Test
    public void testDeleteAtCompleted() {
        givenCompleted("1", "2");
        filterCompleted();

        delete("1");
        assertTasks("2");
        assertItemsLeft(0);
    }

    @Test
    public void testAddAtCompleted() {
        givenCompleted("1");
        filterCompleted();
        add("2");
        assertTasks("1");
        assertItemsLeft(1);
    }

    @Test
    public void testEditAtCompleted() {
        givenCompleted("1", "2");
        filterCompleted();
        edit("2", "2 edit");
        assertTasks("1", "2 edit");
        assertItemsLeft(0);
    }

    @Test
    public void testCompleteAllAtCompleted() {
        givenCompleted("1", "2");
        filterCompleted();
        clearCompleted();
        assertNoTasks();
    }

    @Test
    public void testReopenAllToCompleted() {
        givenCompleted("1");
        filterCompleted();
        toggleAll();
        assertNoTasks();
        assertItemsLeft(1);
    }
}
