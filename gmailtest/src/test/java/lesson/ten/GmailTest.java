package lesson.ten;

import com.codeborne.selenide.Configuration;
import lesson.ten.config.TestData;
import lesson.ten.pages.Gmail;
import lesson.ten.pages.Mail;
import lesson.ten.pages.Menu;
import org.junit.Test;

import java.util.Calendar;

import static lesson.ten.config.TestData.email;
import static lesson.ten.config.TestData.password;
import static lesson.ten.pages.Gmail.*;



public class GmailTest {

    {
        Configuration.timeout = 15000;
    }

    @Test
    public void testLoginSendReceiveSearch() {
        String subject = String.format("New letter %s", Calendar.getInstance().getTime());

        Gmail.visit();
        Gmail.login(TestData.email, TestData.password);
        Mail.sendMail(TestData.email, subject);
        Menu.refresh();

        Mail.assertEmail(0, subject);

        Menu.clickToSent();
        Mail.assertEmail(0, subject);

        Menu.clickToInbox();
        Menu.search(subject);
        Mail.assertEmails(subject);

    }

}
