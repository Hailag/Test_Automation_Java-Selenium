package lesson.ten;

import org.junit.Test;

import static lesson.ten.config.TestData.email;
import static lesson.ten.config.TestData.password;
import static lesson.ten.pages.Gmail.*;
import static lesson.ten.pages.Mail.sendMail;


public class GmailTest {

    @Test
    public void testloginGmail() {

        openPage();
        login(email, password);

        sendMail(email, "new letter"); //TODO Rename


    }



}
