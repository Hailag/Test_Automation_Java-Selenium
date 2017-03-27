package lesson.nine.google;

import org.junit.Test;
import static lesson.nine.google.pages.GoogleSearch.*;

/**
 1 visit http://google.com/ncr
 2 search: “Selenium automates browsers”
 3 check that 10 results are found
 4 check the text “Selenium automates browsers” in 1st result
 5 follow the link in 1st result
 6 check that selenium official page is loaded
**/

public class GoogleSearchTest {

    @Test
    public void SearchAndResultTest (){

        googleMainPage();

        search("Selenium automates browsers");
        assertResult(10);



    }


}
