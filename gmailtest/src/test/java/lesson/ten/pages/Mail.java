package lesson.ten.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selectors;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class Mail {

    public static ElementsCollection emails = $$("[role='main'] .zA");

    public static void sendMail(String email, String text) {
        $(byText("COMPOSE")).click();
        $(By.name("to")).setValue(email);
        $(By.name("subjectbox")).setValue(text);
        $(new Selectors.ByText("Send")).click();

    }

    public static void assertEmail(int index, String emailHeaderText) {
        emails.get(index).shouldHave(text(emailHeaderText));
    }

    public static void assertEmails(String... emailHeaderTexts) {
        emails.shouldHave(texts(emailHeaderTexts));
    }
}
