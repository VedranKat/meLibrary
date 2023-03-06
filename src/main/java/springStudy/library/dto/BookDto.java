package springStudy.library.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import springStudy.library.model.Author;
import springStudy.library.model.BookLoan;
import springStudy.library.model.Publisher;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BookDto {

    private Long id;
    @NotNull(message = "Book must have a title")
    private String title;
    @NotNull(message = "Book must have an isbn")
    //@Size(min = 13, max = 13) temporarily off
    private String isbn;
    private Integer copies;

    private Publisher publisher;

    private Set<Author> authors = new HashSet<>();

    private Set<BookLoan> bookLoans = new HashSet<>();


    public BookDto() {
    }
}