package lesson.ten.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selectors;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;



public class Gmail {

    public static ElementsCollection emails = $$("[role='main'] .zA");

    public static void visit() {
        open("https://gmail.com");
    }

    public static void login (String email, String password) {

        $("#Email").setValue(email).pressEnter();
        $("#Passwd").setValue(password).pressEnter();

    }

    public static void clickToCompose () {
        $(byText("COMPOSE")).click();
    }

    public static void sendMail(String email, String text) {

        $(By.name("to")).setValue(email);
        $(By.name("subjectbox")).setValue(text);
        $(new Selectors.ByText("Send")).click();

    }



    public static void assertArrived (String arrivedMessage) {
        $(".vh").shouldHave(text(arrivedMessage));
    }

    public static void checkSendMail (int index, String emailHeaderText) {
        $(byTitle("Sent Mail")).click();
        emails.get(index).shouldHave(text(emailHeaderText));
    }

    public static void checkInbox (int index, String emailHeaderText){
        $(byTitle("Inbox")).click();
        emails.get(index).shouldHave(text(emailHeaderText));
    }
}
