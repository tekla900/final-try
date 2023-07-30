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

        booksSteps.inputPublisherToSearch()
                .searchBasedOnPublisher()
                .assertBooksNumber(booksSteps.getNumberOfBooks(), booksSteps.getOReillyBooksSizeFromApi())
                .assertLastBookInApi()
                .assertLastBookInUi();
    }
}
