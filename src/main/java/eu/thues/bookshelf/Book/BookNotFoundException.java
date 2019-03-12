package eu.thues.bookshelf.Book;

import javax.persistence.EntityNotFoundException;

public class BookNotFoundException extends EntityNotFoundException {

    public BookNotFoundException(Long id) {
        super("Book with ID " + id.toString() + " not found");
    }
}
