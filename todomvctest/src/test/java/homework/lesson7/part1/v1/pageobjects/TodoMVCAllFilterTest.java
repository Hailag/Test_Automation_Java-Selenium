package homework.lesson7.part1.v1.pageobjects;

import homework.lesson7.part1.v1.pageobjects.pages.TodoMVCPage;
import org.junit.Test;

/**
 * Created by Homr on 2/26/2017.
 */
public class TodoMVCAllFilterTest {
    TodoMVCPage page = new TodoMVCPage();

    @Test
    public void testEditAtAll() {
        page.givenActive("1");
        page.givenCompleted("2");
        page.edit("2", "2 edited");
        page.assertTasks("1", "2 edited");
        page.assertItemsLeft(1);
    }

    @Test
    public void testCancelEditAtAll() {
        page.givenActive("1");
        page.cancelEdit("1", "edited");
        page.assertTasks("1");
        page.assertItemsLeft(1);
    }

    @Test
    public void testDeleteAtAll() {
        page.givenActive("1");
        page.filterAll();
        page.delete("1");
        page.assertNoTasks();
    }
    @Test
    public void testAllCompleteAtAll() {
        page.givenActive("1", "2");
        page.toggleAll();
        page.assertItemsLeft(0);
    }

    @Test
    public void testClearCompleteAtAll() {
        page.givenActive("1", "2");
        page.toggleAll();
        page.clearCompleted();
        page.assertNoTasks();
    }

    @Test
    public void testReopenAtAll() {
        page.givenCompleted("1");
        page.toggle("1");
        page.assertTasks("1");
        page.assertItemsLeft(1);
    }

    @Test
    public void testReopenAllAtAll() {
        page.givenCompleted("1", "2");
        page.toggleAll();
        page.assertTasks("1", "2");
        page.assertItemsLeft(2);
    }
}
