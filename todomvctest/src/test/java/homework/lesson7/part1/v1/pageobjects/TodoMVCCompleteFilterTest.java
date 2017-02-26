package homework.lesson7.part1.v1.pageobjects;

import homework.lesson7.part1.v1.pageobjects.pages.TodoMVCPage;
import org.junit.Test;

public class TodoMVCCompleteFilterTest {
    TodoMVCPage page = new TodoMVCPage();

    @Test
    public void testDeleteAtCompleted() {
        page.givenCompleted("1", "2");
        page.filterCompleted();

        page.delete("1");
        page.assertTasks("2");
        page.assertItemsLeft(0);
    }

    @Test
    public void testAddAtCompleted() {
        page.givenCompleted("1");
        page.filterCompleted();
        page.add("2");
        page.assertTasks("1");
        page.assertItemsLeft(1);
    }

    @Test
    public void testEditAtCompleted() {
        page.givenCompleted("1", "2");
        page.filterCompleted();
        page.edit("2", "2 edit");
        page.assertTasks("1", "2 edit");
        page.assertItemsLeft(0);
    }

    @Test
    public void testCompleteAllAtCompleted() {
        page.givenCompleted("1", "2");
        page.filterCompleted();
        page.clearCompleted();
        page.assertNoTasks();
    }

    @Test
    public void testReopenAllToCompleted() {
        page.givenCompleted("1");
        page.filterCompleted();
        page.toggleAll();
        page.assertNoTasks();
        page.assertItemsLeft(1);
    }
}
