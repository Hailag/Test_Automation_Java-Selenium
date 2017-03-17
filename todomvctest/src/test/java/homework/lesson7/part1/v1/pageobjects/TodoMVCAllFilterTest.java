package homework.lesson7.part1.v1.pageobjects;

import homework.lesson7.part1.v1.pageobjects.pages.TodoMVCPage;
import homework.lesson7.part1.v1.pageobjects.testconfigs.BaseTest;
import org.junit.Test;
import static homework.lesson7.part1.v1.pageobjects.pages.TodoMVCPage.TaskType.*;


public class TodoMVCAllFilterTest extends BaseTest{
    TodoMVCPage page = new TodoMVCPage();

    @Test
    public void testEdit() {
        page.givenAtAll(page.aTask(ACTIVE, "1"), page.aTask(COMPLETED, "2"));

        page.edit("2", "2 edited");
        page.assertTasks("1", "2 edited");
        page.assertItemsLeft(1);
    }

    @Test
    public void testCancelEdit() {
        page.givenAtAll(ACTIVE, "1");

        page.cancelEdit("1", "edited");
        page.assertTasks("1");
        page.assertItemsLeft(1);
    }

    @Test
    public void testDelete() {
        page.givenAtAll(page.aTask(ACTIVE, "1"), page.aTask(COMPLETED, "2"));

        page.delete("1");
        page.assertTasks("2");
        page.assertItemsLeft(0);
    }
    @Test
    public void testCompleteAll() {
        page.givenAtAll(ACTIVE,"1", "2");

        page.toggleAll();
        page.assertTasks("1", "2");
        page.assertItemsLeft(0);
    }

    @Test
    public void testClearCompleted() {
        page.givenAtAll(COMPLETED, "1","2");

        page.clearCompleted();
        page.assertNoTasks();

    }

    @Test
    public void testReopen() {
        page.givenAtAll(COMPLETED,"1");

        page.toggle("1");
        page.assertTasks("1");
        page.assertItemsLeft(1);
    }


    @Test
    public void testCancelEditByEsc() {
        page.givenAtAll(page.aTask(ACTIVE, "1"), page.aTask(COMPLETED, "2"));

        page.cancelEdit("2", "2 edited");
        page.assertTasks("1", "2");
        page.assertItemsLeft(1);
    }

    @Test
    public void editByClickOutOfTask() {
        page.givenAtAll(ACTIVE, "1", "2");

        page.editByClickOutOfTask("2", "2 edited");
        page.assertTasks("1", "2 edited");
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
