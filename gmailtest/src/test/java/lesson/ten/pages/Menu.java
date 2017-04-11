package lesson.ten.pages;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selenide.$;

public class Menu {

    public static void clickToSent (){
        $(byTitle("Sent Mail")).click();
    }

    public static void clickToInbox () {
        $("[title^='Inbox']").click();
    }

    public static void refresh () {
        $(byTitle("Refresh")).click();
    }

    public static void search (String searchText) {
        $(byName("q")).setValue(searchText).pressEnter();
    }
}
