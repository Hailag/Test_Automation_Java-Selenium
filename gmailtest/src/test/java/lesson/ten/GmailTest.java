package lesson.ten;

import org.junit.Test;

import static lesson.ten.config.TestData.email;
import static lesson.ten.config.TestData.password;
import static lesson.ten.pages.Gmail.*;



public class GmailTest {

    @Test
    public void testloginGmail() {

        openPage();
        login(email, password);

        sendMail(email, "new letter");


    }



}
