package homework.lesson7.part1.v1.pageobjects;

import homework.lesson7.part1.v1.pageobjects.pages.TodoMVCPage;
import homework.lesson7.part1.v1.pageobjects.testconfigs.BaseTest;
import org.junit.Test;
import static homework.lesson7.part1.v1.pageobjects.pages.TodoMVCPage.TaskType.*;


public class TodoMVCAllFilterTest extends BaseTest{
    TodoMVCPage page = new TodoMVCPage();

    @Test
    public void testEditAtAll() {
        page.givenAtAll(ACTIVE,"1", "2");
        page.edit("2", "2 edited");
        page.assertTasks("1", "2 edited");
        page.assertItemsLeft(2);
    }

    @Test
    public void testCancelEditAtAll() {
        page.givenAtAll(ACTIVE,"1");
        page.cancelEdit("1", "edited");
        page.assertTasks("1");
        page.assertItemsLeft(1);
    }

    @Test
    public void testDeleteAtAll() {
        page.givenAtAll(ACTIVE,"1");

        page.delete("1");
        page.assertNoTasks();
    }
    @Test
    public void testAllCompleteAtAll() {
        page.givenAtAll(ACTIVE,"1", "2");
        page.toggleAll();
        page.assertItemsLeft(0);
    }

    @Test
    public void testClearCompleteAtAll() {
        page.givenAtAll(ACTIVE,"1", "2");
        page.toggleAll();
        page.clearCompleted();
        page.assertNoTasks();
    }

    @Test
    public void testReopenAtAll() {
        page.givenAtAll(COMPLETED,"1");
        page.toggle("1");
        page.assertTasks("1");
        page.assertItemsLeft(1);
    }

    @Test
    public void testReopenAllAtAll() {
        page.givenAtAll(COMPLETED,"1", "2");
        page.toggleAll();
        page.assertTasks("1", "2");
        page.assertItemsLeft(2);
    }

    @Test
    public void testSwitchFromAllToCompleted() {
        page.givenAtAll(page.aTask(ACTIVE, "1"), page.aTask(COMPLETED, "2"));

        page.filterCompleted();
        page.assertTasks( "2");
        page.assertItemsLeft(1);
    }
}
