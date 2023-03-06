package springStudy.library.dto;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import springStudy.library.model.Book;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BookLoanDto {

    private Long id;
    @NotNull(message = "BookLoan must have a Book")
    private Book book;
    private String userName;
    private Date loanDate;
    private Date returnDate;

    public BookLoanDto() {
    }
}
