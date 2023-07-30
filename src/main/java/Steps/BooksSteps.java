package Steps;

import Data.BooksData;
import Pages.BooksPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.asynchttpclient.util.Assertions;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;

public class BooksSteps {

    BooksData booksData = new BooksData();
    BooksPage booksPage = new BooksPage();

    @Step
    public BooksSteps inputPublisherToSearch() {
        booksPage.searchBox.setValue(booksData.publisherToSearch);
        return this;
    }

    @Step
    public BooksSteps searchBasedOnPublisher() {
        booksPage.searchBtn.click();
        return this;
    }

    public int getNumberOfBooks() {
        int expectedCount = booksPage.publisherElements.size();
        return expectedCount;
    }

    @Step
    public BooksSteps assertBooksNumber(int uiBookCount, int apiBookCount) {
        assertEquals(uiBookCount, apiBookCount);
        return this;
    }

    @Step
    public BooksSteps assertLastBookInUi() {
        System.out.println(booksPage.lastBook.getText() + "\n");
//        System.out.println(booksPage.lastBook);

//        Assert.assertEquals(booksPage.lastBook.getText(), booksData.expectedTitle);

        System.out.println(booksPage.books.get(7) + " index7\n");
        for (SelenideElement book:
                booksPage.books) {
            System.out.println("book: " + book + "\n");
            System.out.println("text: " + book.getText() + "\n");
        }

//        booksPage.lastBook.getText().should(Condition.matchText(booksData.expectedTitle));
        return this;
    }

    @Step
    public BooksSteps assertLastBookInApi(int indexOfBook, int lastIndex) {
        assertEquals(indexOfBook,lastIndex);

        return this;
    }
}
