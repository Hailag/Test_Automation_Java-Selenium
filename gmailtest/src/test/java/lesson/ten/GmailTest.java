package lesson.ten;

import com.codeborne.selenide.Configuration;
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

        visit();
        login(email, password);
        clickToCompose();
        sendMail(email, subject);
        refresh();

        assertEmail(0, subject);

        clickToSent();
        assertEmail(0, subject);

        clickToInbox();
        search(subject);
        assertEmails(subject);
    }

    String subject = String.format("New letter %s", Calendar.getInstance().getTime());


}
