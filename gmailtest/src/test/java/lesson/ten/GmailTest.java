package lesson.ten;

import org.junit.Test;

import static lesson.ten.config.TestData.email;
import static lesson.ten.config.TestData.password;
import static lesson.ten.config.TestData.subject;
import static lesson.ten.pages.Gmail.*;



public class GmailTest {

    @Test
    public void testloginGmail() {

        openPage();
        login(email, password);

        sendMail(email, subject);
        assertArrived("Your message has been sent.");

        checkSendMail(0, subject);
        checkInbox(0, subject);

    }



}
