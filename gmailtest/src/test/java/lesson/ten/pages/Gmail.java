package lesson.ten.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selectors;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
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

    public static void clickToSent (){
        $(byTitle("Sent Mail")).click();
    }

    public static void clickToInbox () {
        $(byTitle("Inbox")).click();
    }

    public static void sendMail(String email, String text) {

        $(By.name("to")).setValue(email);
        $(By.name("subjectbox")).setValue(text);
        $(new Selectors.ByText("Send")).click();

    }

    public static void refresh () {
        $(byTitle("Refresh")).click();
    }

    public static void search (String searchTexts) {
        $(byName("q")).setValue(searchTexts).pressEnter();
    }

    public static void assertEmail(int index, String emailHeaderText) {
        emails.get(index).shouldHave(text(emailHeaderText));
    }

    public static void assertEmails(String... emailHeaderTexts) {
        emails.shouldHave(texts(emailHeaderTexts));
    }
}
