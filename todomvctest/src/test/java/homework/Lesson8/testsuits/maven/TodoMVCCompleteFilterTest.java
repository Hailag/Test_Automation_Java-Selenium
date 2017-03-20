package homework.Lesson8.testsuits.maven;

import homework.Lesson8.testsuits.maven.pages.TodoMVCPage;
import homework.Lesson8.testsuits.maven.testconfigs.BaseTest;
import org.junit.Test;

import static homework.Lesson8.testsuits.maven.pages.TodoMVCPage.TaskType.*;


public class TodoMVCCompleteFilterTest extends BaseTest {
    TodoMVCPage page = new TodoMVCPage();


    @Test
    public void testDelete() {
        page.givenAtCompleted(COMPLETED,"1", "2");

        page.delete("1");
        page.assertTasks("2");
        page.assertItemsLeft(0);
    }

    @Test
    public void testEdit() {
        page.givenAtCompleted(COMPLETED, "1", "2");

        page.edit("2", "2 edit");
        page.assertTasks("1", "2 edit");
        page.assertItemsLeft(0);
    }


    @Test
    public void testReopenAll() {
        page.givenAtCompleted(COMPLETED, "1");

        page.toggleAll();
        page.assertNoTasks();
        page.assertItemsLeft(1);
    }

    @Test
    public void testDeleteByClear() {
        page.givenAtCompleted(COMPLETED, "1", "2");

        page.edit("2", "");
        page.assertTasks("1");
        page.assertItemsLeft(0);
    }


    @Test
    public void testSwitchFromCompletedToActive() {
        page.givenAtCompleted(page.aTask(ACTIVE, "1"), page.aTask(COMPLETED, "2"));

        page.filterActive();
        page.assertTasks( "1");
        page.assertItemsLeft(1);
    }
}
