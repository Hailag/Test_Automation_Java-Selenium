package lesson_8.features;

import lesson_8.categories.Buggy;
import lesson_8.categories.Smoke;
import lesson_8.pages.TodoMVCPage;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static lesson_8.pages.TodoMVCPage.TaskType.ACTIVE;
import static lesson_8.pages.TodoMVCPage.TaskType.COMPLETED;


public class TodoOperationsAtAllFilterTest {
    TodoMVCPage page = new TodoMVCPage();


    @Test
    public void testEdit() {
        page.givenAtActive(ACTIVE, "1");

        page.edit("1", "1 edited");
        page.assertTasks( "1 edited");
        page.assertItemsLeft(1);
    }

    @Test
    @Category(Smoke.class)
    public void testDelete() {
        page.givenAtAll(page.aTask(ACTIVE, "1"), page.aTask(COMPLETED, "2"));

        page.delete("1");
        page.assertTasks("2");
        page.assertItemsLeft(0);
    }

    @Test
    public void testComplete() {
        page.givenAtActive(ACTIVE,"1", "2");

        page.toggle("1");
        page.assertTasks("2");
        page.assertItemsLeft(1);

    }

    @Test
    public void testCompleteAll() {
        page.givenAtAll(ACTIVE,"1", "2");

        page.toggleAll();
        page.assertTasks("1", "2");
        page.assertItemsLeft(0);
    }

    @Test
    public void testReopen() {
        page.givenAtAll(COMPLETED,"1");

        page.toggle("1");
        page.assertTasks("1");
        page.assertItemsLeft(1);
    }

    @Test
    public void testReopenAll() {
        page.givenAtCompleted(COMPLETED, "1");

        page.toggleAll();
        page.assertNoTasks();
        page.assertItemsLeft(1);
    }

    @Test
    @Category(Buggy.class)
    public void testClearCompleted() {
        page.givenAtActive(page.aTask(ACTIVE, "1"), page.aTask(COMPLETED, "2"), page.aTask(COMPLETED, "3"));

        page.clearCompleted();
        page.assertTasks("2", "3");
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
    public void testDeleteByClear() {
        page.givenAtCompleted(COMPLETED, "1", "2");

        page.edit("2", "");
        page.assertTasks("1");
        page.assertItemsLeft(0);
    }

    @Test
    public void testCancelEditByEsc() {
        page.givenAtAll(page.aTask(ACTIVE, "1"), page.aTask(COMPLETED, "2"));

        page.cancelEdit("2", "2 edited");
        page.assertTasks("1", "2");
        page.assertItemsLeft(1);
    }

    @Test
    public void testEditByClickOutOfTask() {
        page.givenAtAll(ACTIVE, "1", "2");

        page.editByClickOutOfTask("2", "2 edited");
        page.assertTasks("1", "2 edited");
        page.assertItemsLeft(2);
    }

    @Test
    public void testEditByTAB() {
        page.givenAtActive(page.aTask(ACTIVE, "1"), page.aTask(COMPLETED, "2"));

        page.editByTab("1", "1 edited");
        page.assertTasks("1 edited");
        page.assertItemsLeft(1);
    }

}
