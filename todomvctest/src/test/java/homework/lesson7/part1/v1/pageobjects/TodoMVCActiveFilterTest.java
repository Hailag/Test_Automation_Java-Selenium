package homework.lesson7.part1.v1.pageobjects;

import homework.lesson7.part1.v1.pageobjects.pages.TodoMVCPage;
import homework.lesson7.part1.v1.pageobjects.testconfigs.BaseTest;
import org.junit.Test;
import static homework.lesson7.part1.v1.pageobjects.pages.TodoMVCPage.TaskType.*;


public class TodoMVCActiveFilterTest extends BaseTest {
    TodoMVCPage page = new TodoMVCPage();

    @Test
    public void testEdit() {
        page.givenAtActive(ACTIVE,"1", "2");

        page.edit("2", "2 edited");
        page.assertTasks("1", "2 edited");
        page.assertItemsLeft(2);
    }

    @Test
    public void testDelete() {
        page.givenAtActive(ACTIVE, "1", "2");

        page.delete("2");
        page.assertTasks("1");
        page.assertItemsLeft(1);
    }


    @Test
    public void testComplete() {
        page.givenAtActive(ACTIVE,"1", "2");

        page.toggleAll();
        page.assertItemsLeft(0);
        // TODO Проверить состояние тасок.
    }

    @Test
    public void testClearComplete() { //Исправить
        page.givenAtActive(ACTIVE, "1");

        page.clearCompleted();
        page.assertNoTasks();
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
        page.assertItemsLeft(1);
    }

}

