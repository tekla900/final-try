package Steps;

import Data.BooksData;
import Models.Book;
import Models.BookList;
import Pages.BooksPage;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.refresh;
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
        return booksPage.publisherElements.size();
    }

    @Step
    public BooksSteps assertBooksNumber(int uiBookCount, int apiBookCount) {
        assertEquals(uiBookCount, apiBookCount);
        return this;
    }

    @Step
    public BooksSteps assertLastBookInUi() {
        refresh();
        assertEquals(booksPage.books.get(booksPage.books.size()-1).getText(), booksData.expectedTitle);
        return this;
    }

    @Step
    public List<Book> getBookList() {
        Response response = RestAssured.given().when()
                .get("https://bookstore.toolsqa.com/BookStore/v1/Books");
        BookList booksList= response
                .jsonPath()
                .getObject("", BookList.class);

        List<Book> books = booksList.books;

        return books;
    }

    @Step
    public int getOReillyBooksSizeFromApi() {

        List<Book> booksWithPublisherOReillyMedia = getBookList().stream().filter(each -> each.publisher.equals("O'Reilly Media")).collect(Collectors.toList());

        return booksWithPublisherOReillyMedia.size();
    }

    @Step
    public BooksSteps assertLastBookInApi() {

        Book book = getBookList().stream().filter(each -> each.title.equals("Understanding ECMAScript 6")).collect(Collectors.toList()).get(0);
        int indexOfBook = getBookList().indexOf(book);
        int lastIndex = getBookList().size() - 1;

        assertEquals(indexOfBook,lastIndex);
        return this;
    }
}
