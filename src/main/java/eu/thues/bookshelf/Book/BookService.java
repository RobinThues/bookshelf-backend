package eu.thues.bookshelf.Book;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    Book createBook(Book book) {
        return bookRepository.save(book);
    }
}
