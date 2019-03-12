package eu.thues.bookshelf.Author;

import eu.thues.bookshelf.Book.Book;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 100)
    @NonNull
    private String firstName;

    @Size(max = 100)
    @NonNull
    private String lastName;

    private Date dateOfBirth;

    @ManyToMany
    private List<Book> books;

    public Author() {

    }
}
