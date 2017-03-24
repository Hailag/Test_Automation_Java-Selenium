package lesson_8.features;

import com.codeborne.selenide.Configuration;
import lesson_8.pages.TodoMVCPage;
import lesson_8.categories.Smoke;
import lesson_8.testconfigs.BaseTest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(Smoke.class)
public class TodoMVCTest {

    @BeforeClass
    public static void setup (){
        Configuration.browser = System.getProperty("driver.browser");
    }

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
