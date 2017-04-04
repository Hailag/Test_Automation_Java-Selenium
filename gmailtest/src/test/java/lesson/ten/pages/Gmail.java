package lesson.ten.pages;

import com.codeborne.selenide.Selectors;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


/**
 * Created by o.iakovenko on 03.04.2017.
 */
public class Gmail {

    public static void openPage() {
        open("https://gmail.com");
    }

    public static void login (String email, String password) {

        $("#Email").setValue(email).pressEnter();
        $("#Passwd").setValue(password).pressEnter();

    }

    public static void sendMail(String email, String text) {

        $(byText("COMPOSE")).click();
        $(By.name("to")).setValue(email);
        $(By.name("subjectbox")).setValue(text);
        $(new Selectors.ByText("Send")).click();

    }
}
