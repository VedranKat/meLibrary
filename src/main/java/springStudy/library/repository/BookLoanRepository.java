package springStudy.library.repository;

import org.springframework.data.repository.CrudRepository;
import springStudy.library.model.BookLoan;

public interface BookLoanRepository extends CrudRepository<BookLoan, Long> {
}
