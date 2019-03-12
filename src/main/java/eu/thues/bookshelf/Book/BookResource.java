package eu.thues.bookshelf.Book;

import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@Getter
public class BookResource extends ResourceSupport {
    private final Book book;

    public BookResource(final Book book) {
        this.book = book;
        final long id = book.getId();
        add(linkTo(BookController.class).withRel("books"));
        add(linkTo(methodOn(BookController.class).getBook(id)).withSelfRel());
    }
}
