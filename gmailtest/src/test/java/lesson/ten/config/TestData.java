package lesson.ten.config;

import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Selenide.open;


public class TestData {

    {
        Configuration.timeout = 15000;
    }

    public static String email = "qa.iakovenko@gmail.com";

    public static String password = "Test_1234";

    public static void openPage() {
        open("https://accounts.google.com/ServiceLogin?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1#identifier");
    }
}
