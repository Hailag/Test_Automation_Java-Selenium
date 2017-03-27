package lesson.nine.google.pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class GoogleSearch {

    public static void googleMainPage() {
        open("http://google.com/ncr");
    }

    public static void search(String searchText){
        $(By.name("q")).setValue(searchText).submit();
    }

    public static void assertResult(int count){ // как реализовать ожидаемый результат? Где почитать?

    }


}
