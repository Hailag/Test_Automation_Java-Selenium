package homework.lesson7.part1.v1.pageobjects;

import homework.lesson7.part1.v1.pageobjects.pages.TodoMVCPage;
import homework.lesson7.part1.v1.pageobjects.testconfigs.BaseTest;
import org.junit.Test;
import static homework.lesson7.part1.v1.pageobjects.pages.TodoMVCPage.TaskType.*;


public class TodoMVCActiveFilterTest extends BaseTest {
    TodoMVCPage page = new TodoMVCPage();

    @Test
    public void testEditAtActive() {
        page.givenAtActive(ACTIVE,"1", "2");

        page.edit("2", "2 edited");
        page.assertTasks("1", "2 edited");
        page.assertItemsLeft(2);
    }

    @Test
    public void testDeleteAtActive() {
        page.givenAtActive(ACTIVE, "1", "2");

        page.delete("2");
        page.assertTasks("1");
        page.assertItemsLeft(1);
    }


    @Test
    public void testCompleteAtActive() {
        page.givenAtActive(ACTIVE,"1", "2");

        page.toggleAll();
        page.assertItemsLeft(0);
    }

    @Test
    public void testClearCompleteAtActive() { //Исправить
        page.givenAtActive(ACTIVE, "1");

        page.clearCompleted();
        page.assertNoTasks();
    }

    @Test
    public void testReopenAllAtActive() {
        page.givenAtActive(COMPLETED,"1", "2");

        page.assertNoTasks();
        page.toggleAll();
        page.assertItemsLeft(2);
    }

    @Test
    public void testSwitchFromActiveToAll() {
        page.givenAtActive(page.aTask(ACTIVE, "1"), page.aTask(COMPLETED, "2"));

        page.filterAll();
        page.assertTasks("1", "2");
        page.assertItemsLeft(1);
    }

    @Test
    public void testSwitchFromActiveToCompleted() {
        page.givenAtActive(page.aTask(ACTIVE, "1"), page.aTask(COMPLETED, "2"));

        page.filterCompleted();
        page.assertTasks( "2");
        page.assertItemsLeft(1);
    }

}

