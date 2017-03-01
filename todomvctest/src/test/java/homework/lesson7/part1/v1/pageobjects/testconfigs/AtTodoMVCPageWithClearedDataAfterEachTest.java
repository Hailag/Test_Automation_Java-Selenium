package homework.lesson7.part1.v1.pageobjects.testconfigs;

import org.junit.After;
import org.junit.Before;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by Homr on 2/26/2017.
 */
public class AtTodoMVCPageWithClearedDataAfterEachTest extends BaseTest {
    @Before
    public void openPage() {
        open("https://todomvc4tasj.herokuapp.com/");
    }

    @After
    public void clearData() {
        executeJavaScript("localStorage.clear()");
    }
}
