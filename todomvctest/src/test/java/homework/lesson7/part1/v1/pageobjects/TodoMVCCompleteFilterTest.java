package homework.lesson7.part1.v1.pageobjects;

import homework.lesson7.part1.v1.pageobjects.testconfigs.BaseTest;
import homework.lesson7.part1.v1.pageobjects.pages.TodoMVCPage;
import org.junit.Test;
import static homework.lesson7.part1.v1.pageobjects.pages.TodoMVCPage.TaskType.*;



public class TodoMVCCompleteFilterTest extends BaseTest {
    TodoMVCPage page = new TodoMVCPage();

    @Test
    public void testDeleteAtCompleted() {
        page.givenAtCompleted(COMPLETED,"1", "2");

        page.delete("1");
        page.assertTasks("2");
        page.assertItemsLeft(0);
    }

    @Test
    public void testAddAtCompleted() {
        page.givenAtCompleted(COMPLETED, "1");
        page.add("2");
        page.assertTasks("1");
        page.assertItemsLeft(1);
    }

    @Test
    public void testEditAtCompleted() {
        page.givenAtCompleted(COMPLETED, "1", "2");

        page.edit("2", "2 edit");
        page.assertTasks("1", "2 edit");
        page.assertItemsLeft(0);
    }

    @Test
    public void testCompleteAllAtCompleted() {
        page.givenAtCompleted(COMPLETED, "1", "2");

        page.toggleAll();
        page.assertTasks("1","2");
        page.assertItemsLeft(0);
    }

    @Test
    public void testReopenAllToCompleted() {
        page.givenAtCompleted(COMPLETED, "1");

        page.toggleAll();
        page.assertNoTasks();
        page.assertItemsLeft(1);
    }

    @Test
    public void testSwitchFromCompletedToAll() {
        page.givenAtCompleted(page.aTask(ACTIVE, "1"), page.aTask(COMPLETED, "2"));

        page.filterAll();
        page.assertTasks( "1", "2");
        page.assertItemsLeft(1);
    }

    @Test
    public void testSwitchFromCompletedToActive() {
        page.givenAtCompleted(page.aTask(ACTIVE, "1"), page.aTask(COMPLETED, "2"));

        page.filterActive();
        page.assertTasks( "1");
        page.assertItemsLeft(1);
    }
}
