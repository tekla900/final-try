package Models.Response;

import Models.Response.Book;
import lombok.Data;

import java.util.List;

@Data
public class BooksResponse {
    public List<Book> books;
}