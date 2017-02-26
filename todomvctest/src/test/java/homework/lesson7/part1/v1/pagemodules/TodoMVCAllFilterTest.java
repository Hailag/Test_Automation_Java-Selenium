package homework.lesson7.part1.v1.pagemodules;

import homework.lesson7.part1.v1.pagemodules.pages.Tasks;
import org.junit.Test;

import static homework.lesson7.part1.v1.pagemodules.pages.Tasks.*;

public class TodoMVCAllFilterTest {
    
    @Test
    public void testEditAtAll() {
        givenActive("1");
        givenCompleted("2");
        edit("2", "2 edited");
        assertTasks("1", "2 edited");
        assertItemsLeft(1);
    }

    @Test
    public void testCancelEditAtAll() {
        givenActive("1");
        cancelEdit("1", "edited");
        assertTasks("1");
        assertItemsLeft(1);
    }

    @Test
    public void testDeleteAtAll() {
        givenActive("1");
        filterAll();
        delete("1");
        assertNoTasks();
    }
    @Test
    public void testAllCompleteAtAll() {
        givenActive("1", "2");
        toggleAll();
        assertItemsLeft(0);
    }

    @Test
    public void testClearCompleteAtAll() {
        givenActive("1", "2");
        toggleAll();
        clearCompleted();
        assertNoTasks();
    }

    @Test
    public void testReopenAtAll() {
        givenCompleted("1");
        toggle("1");
        assertTasks("1");
        assertItemsLeft(1);
    }

    @Test
    public void testReopenAllAtAll() {
        givenCompleted("1", "2");
        toggleAll();
        assertTasks("1", "2");
        assertItemsLeft(2);
    }
}
