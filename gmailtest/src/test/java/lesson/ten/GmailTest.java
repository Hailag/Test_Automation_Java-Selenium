package lesson.ten;

import com.codeborne.selenide.Configuration;
import org.junit.Test;

import static lesson.ten.config.TestData.email;
import static lesson.ten.config.TestData.password;
import static lesson.ten.pages.Gmail.*;



public class GmailTest {

    {
        Configuration.timeout = 15000;
    }

    @Test
    public void testloginGmail() {

        visit();
        login(email, password);

        clickToCompose();
        sendMail(email, subject);
        assertArrived("Your message has been sent.");

        checkSendMail(0, subject);
        checkInbox(0, subject);
    }

    String subject = "new letter"; // TODO текст значением даты-времени в миллисекундах



}
