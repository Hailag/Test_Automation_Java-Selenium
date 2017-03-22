package homework.Lesson8.testsuits.maven;

import homework.Lesson8.testsuits.maven.pages.TodoMVCPage;
import org.junit.Test;


public class TodosE2ETest {

    TodoMVCPage page = new TodoMVCPage();

    @Test
    public void testTasksLifeCycle() {
        page.given();

        page.add("1");
        page.toggle("1");
        page.assertTasks("1");

        page.filterActive();
        page.assertNoTasks();
        page.add("2");
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
