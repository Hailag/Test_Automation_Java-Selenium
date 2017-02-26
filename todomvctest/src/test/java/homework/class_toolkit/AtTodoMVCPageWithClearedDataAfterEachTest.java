package homework.class_toolkit;

import org.junit.After;
import org.junit.Before;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by Homr on 2/26/2017.
 */
public class AtTodoMVCPageWithClearedDataAfterEachTest extends homework.class_toolkit.BaseTest{
    @Before
    public void openPage() {
        open("https://todomvc4tasj.herokuapp.com/");
    }

    @After
    public void clearData() {
        executeJavaScript("localStorage.clear()");
    }
}
