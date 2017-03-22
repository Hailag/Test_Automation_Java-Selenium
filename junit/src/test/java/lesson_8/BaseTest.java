package lesson_8;

import com.codeborne.selenide.Configuration;

/**
 * Created by o.iakovenko on 22.03.2017.
 */
public class BaseTest {
    {
        Configuration.browser = System.getProperty("driver.browser");
    }
}
