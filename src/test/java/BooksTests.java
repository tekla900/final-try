import Models.Book;
import Models.BookList;
import Steps.BooksSteps;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

//@Listeners(Config.class)
@Epic("bookstore testing")
@Feature("bookstore test cases")
public class BooksTests extends Config {

    BooksSteps booksSteps = new BooksSteps();

    @Test
    @Description("Validate books with publisher name")
    @Story("validate books")
    public void validateBooksByPublisherInUI() {

        Response response = RestAssured.given().when()
                .get("https://bookstore.toolsqa.com/BookStore/v1/Books");
        BookList booksList= response
                .jsonPath()
                .getObject("", BookList.class);

        List<Book> books = booksList.books;
        List<Book> booksWithPublisherOReillyMedia = books.stream().filter(x->x.publisher.equals("O'Reilly Media")).collect(Collectors.toList());

        int bookCount = booksWithPublisherOReillyMedia.size();

        Book book = books.stream().filter(x->x.title.equals("Understanding ECMAScript 6")).collect(Collectors.toList()).get(0);
        int indexOfBook = books.indexOf(book);
        int lastIndex = books.size() - 1;

        booksSteps.inputPublisherToSearch()
                .searchBasedOnPublisher()
                .assertBooksNumber(booksSteps.getNumberOfBooks(), bookCount)
                .assertLastBookInApi(indexOfBook, lastIndex)
                .assertLastBookInUi();
    }
}
