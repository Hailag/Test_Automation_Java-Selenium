package homework.lesson7.part1.v1.pageobjects;

import homework.lesson7.part1.v1.pageobjects.pages.TodoMVCPage;
import homework.lesson7.part1.v1.pageobjects.testconfigs.AtTodoMVCPageWithClearedDataAfterEachTest;
import org.junit.Test;


public class TodoMVCActiveFilterTest extends AtTodoMVCPageWithClearedDataAfterEachTest {
    TodoMVCPage page = new TodoMVCPage();

    @Test
    public void testEditAtActive() {
        page.givenActive("1", "2");
        page.filterActive();
        page.edit("2", "2 edited");
        page.assertTasks("1", "2 edited");
        page.assertItemsLeft(2);
    }

    @Test
    public void testDeleteAtActive() {
        page.givenActive("1");
        page.filterActive();
        page.delete("1");
        page.assertNoTasks();
        page.assertItemsLeft(0);
    }

    @Test
    public void testCompleteAtActive() {
        page.givenActive("1", "2");
        page.filterActive();
        page.toggleAll();
        page.assertItemsLeft(0);
    }

    @Test
    public void testClearCompleteAtActive() {
        page.givenActive("1");
        page.filterActive();
        page.toggle("1");
        page.clearCompleted();
        page.assertItemsLeft(0);
    }

    @Test
    public void testReopenAllAtActive() {
        page.givenCompleted("1", "2");
        page.filterActive();
        page.assertNoTasks();
        page.toggleAll();
        page.assertItemsLeft(2);
    }

}

