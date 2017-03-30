package lesson.nine.google;

import com.codeborne.selenide.ElementsCollection;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;

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
    public void searchAndFollowLink(){

        visit();

        search("Selenium automates browsers");
        assertResultsCount(10);
        results.get(0).shouldHave(text("Selenium automates browsers"));
        results.get(0).$(".r>a").click();

        $("#header>h1").shouldHave(text("Browser Automation"));
        assertEquals("http://www.seleniumhq.org/", url());
    }


    private ElementsCollection results = $$("#rso>._NId>.g>.rc,.srg>.g");// По другому никак не получалось

    private void visit() {
        open("http://google.com/ncr");
    }

    private static void search(String searchText) {
        $(By.name("q")).setValue(searchText).pressEnter();
    }

    private void assertResultsCount(int count) {
        results.shouldHaveSize(count);
    }

}
