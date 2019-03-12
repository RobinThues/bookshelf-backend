package eu.thues.bookshelf.Book;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookService.findAllBooks();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postBook(@RequestBody Book book) {
        bookService.createBook(book);
    }
}
