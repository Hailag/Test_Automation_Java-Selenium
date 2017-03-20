package homework.Lesson8.testsuits.maven;

import homework.Lesson8.testsuits.maven.pages.TodoMVCPage;
import homework.Lesson8.testsuits.maven.testconfigs.BaseTest;
import org.junit.Test;

import static homework.Lesson8.testsuits.maven.pages.TodoMVCPage.TaskType.*;


public class TodoMVCActiveFilterTest extends BaseTest {
    TodoMVCPage page = new TodoMVCPage();

    @Test
    public void testEdit() {
        page.givenAtActive(ACTIVE, "1");

        page.edit("1", "1 edited");
        page.assertTasks( "1 edited");
        page.assertItemsLeft(1);
    }

    @Test
    public void testDelete() {
        page.givenAtActive(ACTIVE, "1");

        page.delete("1");
        page.assertNoTasks();
    }


    @Test
    public void testComplete() {
        page.givenAtActive(ACTIVE,"1", "2");

        page.toggle("1");
        page.assertTasks("2");
        page.assertItemsLeft(1);

    }

    @Test
    public void testClearCompleted() {
        page.givenAtActive(page.aTask(ACTIVE, "1"), page.aTask(COMPLETED, "2"), page.aTask(COMPLETED, "3"));

        page.clearCompleted();
        page.assertTasks("1");
        page.assertItemsLeft(1);
    }


    @Test
    public void testCancelEdit() {
        page.givenAtActive(page.aTask(ACTIVE, "1"), page.aTask(COMPLETED, "2"));

        page.cancelEdit("1", "1 edited");
        page.assertTasks("1");
        page.assertItemsLeft(1);
    }

    @Test
    public void testEditByTAB() {
        page.givenAtActive(page.aTask(ACTIVE, "1"), page.aTask(COMPLETED, "2"));

        page.editByTab("1", "1 edited");
        page.assertTasks("1 edited");
        page.assertItemsLeft(1);
    }

    @Test
    public void testSwitchFromActiveToAll() {
        page.givenAtActive(page.aTask(ACTIVE, "1"), page.aTask(COMPLETED, "2"));

        page.filterAll();
        page.assertTasks("1", "2");
        page.assertItemsLeft(2);
    }

}

