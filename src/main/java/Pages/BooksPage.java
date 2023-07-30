package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BooksPage {
    public SelenideElement
        searchBox = $("#searchBox"),
        searchBtn = $("#basic-addon2"),
        lastBook = $$(".rt-tr").get(7);

    public List<SelenideElement> publisherElements = $$("[class='rt-td']").filter(Condition.text("O'Reilly Media")),
        books =  $$(".rt-tr-group");

}
