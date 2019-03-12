package eu.thues.bookshelf.Book;

import eu.thues.bookshelf.Author.Author;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String isbn;

    @NonNull
    private String title;

    @ManyToMany
    private List<Author> authors;

    public Book() {

    }
}
