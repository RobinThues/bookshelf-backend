package eu.thues.bookshelf.Book;

import eu.thues.bookshelf.Routes;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(BookRoutes.BASE)
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Resources<BookResource>> all() {
        final List<BookResource> collection = bookService.findAllBooks()
                .stream()
                .map(BookResource::new)
                .collect(Collectors.toList());

        final Resources<BookResource> resources = new Resources<>(collection);
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        resources.add(new Link(uriString, "self"));

        return ResponseEntity.ok(resources);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookResource> postBook(@RequestBody Book book) {
        Book createdBook = bookService.createBook(book);

        final URI uri = MvcUriComponentsBuilder.fromController(getClass())
                .path("/{id}")
                .buildAndExpand(createdBook.getId())
                .toUri();
        return ResponseEntity.created(uri).body(new BookResource(createdBook));
    }

    @GetMapping(BookRoutes.ID)
    public HttpEntity<Book> getBook(@PathVariable Long id) {
        try {
            Book book = bookService.findById(id);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (BookNotFoundException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex
            );
        }
    }

    @GetMapping(BookRoutes.TITLE)
    public List<Book> GetBooksByTitle(@PathVariable String title) {
        return bookService.findByTitle(title);
    }
}
