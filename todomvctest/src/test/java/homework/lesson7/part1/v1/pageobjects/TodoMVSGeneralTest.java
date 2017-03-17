package homework.lesson7.part1.v1.pageobjects;

import homework.lesson7.part1.v1.pageobjects.pages.TodoMVCPage;
import org.junit.Test;
import static homework.lesson7.part1.v1.pageobjects.pages.TodoMVCPage.TaskType.*;


public class TodoMVSGeneralTest {

    TodoMVCPage page = new TodoMVCPage();

    @Test
    public void testTasksLifeCycle() {

        page.givenAtAll(ACTIVE, "1");
        page.toggle("1");
        page.assertTasks("1");

        page.filterActive();
        page.assertNoTasks();
        page.givenAtActive(page.aTask(COMPLETED, "1"), page.aTask(ACTIVE, "2"));
        page.toggleAll();
        page.assertNoTasks();

        page.filterCompleted();
        page.assertTasks("1", "2");
        page.toggle("2");
        page.assertTasks("1");
        page.clearCompleted();
        page.assertNoTasks();

        page.filterAll();
        page.assertTasks("2");
    }

}
