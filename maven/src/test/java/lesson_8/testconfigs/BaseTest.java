package lesson_8.testconfigs;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Screenshots;
import com.google.common.io.Files;
import org.junit.After;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;


public class BaseTest {

    {
        Configuration.browser = System.getProperty("driver.browser");
    }

}
