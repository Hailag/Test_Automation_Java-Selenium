package lesson.ten.config;

import com.codeborne.selenide.Configuration;

public class TestData {

    {
        Configuration.timeout = 15000;
    }

    public static String email = "qa.iakovenko@gmail.com";
    public static String password = "Test_1234";


}
