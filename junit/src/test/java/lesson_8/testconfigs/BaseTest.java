package lesson_8.testconfigs;

import com.codeborne.selenide.Configuration;


public class BaseTest {

    {
        Configuration.browser = System.getProperty("driver.browser");
    }

}
