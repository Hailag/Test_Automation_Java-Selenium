package homework.lesson7.part1.v1.pageobjects;

import homework.lesson7.part1.v1.pageobjects.pages.TodoMVCPage;
import homework.lesson7.part1.v1.pageobjects.testconfigs.BaseTest;
import org.junit.Test;

import static homework.lesson7.part1.v1.pageobjects.pages.TodoMVCPage.TaskStatus.*;


public class TodoMVCActiveFilterTest extends BaseTest {
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
        page.givenActive("1", "2");
        page.filterActive();
        page.delete("2");
        page.assertTasks("1");
        page.assertItemsLeft(1);
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
        page.givenAtActive(ACTIVE, "1"); // переделал
        page.toggle("1");
        page.clearCompleted();
        page.assertNoTasks();
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

