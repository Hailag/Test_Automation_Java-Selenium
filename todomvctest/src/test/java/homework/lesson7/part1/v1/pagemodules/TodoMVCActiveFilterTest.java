package homework.lesson7.part1.v1.pagemodules;

import homework.lesson7.part1.v1.pagemodules.pages.Tasks;
import org.junit.Test;

import static homework.lesson7.part1.v1.pagemodules.pages.Tasks.*;

/**
 * Created by Homr on 2/26/2017.
 */
public class TodoMVCActiveFilterTest {
    
    @Test
    public void testEditAtActive() {
        givenActive("1", "2");
        filterActive();
        edit("2", "2 edited");
        assertTasks("1", "2 edited");
        assertItemsLeft(2);
    }

    @Test
    public void testDeleteAtActive() {
        givenActive("1");
        filterActive();
        delete("1");
        assertNoTasks();
        assertItemsLeft(0);
    }

    @Test
    public void testCompleteAtActive() {
        givenActive("1", "2");
        filterActive();
        toggleAll();
        assertItemsLeft(0);
    }

    @Test
    public void testClearCompleteAtActive() {
        givenActive("1");
        filterActive();
        toggle("1");
        clearCompleted();
        assertItemsLeft(0);
    }

    @Test
    public void testReopenAllAtActive() {
        givenCompleted("1", "2");
        filterActive();
        toggleAll();
        assertItemsLeft(2);
    }

}

