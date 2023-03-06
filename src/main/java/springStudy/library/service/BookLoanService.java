package springStudy.library.service;

import springStudy.library.model.BookLoan;

public interface BookLoanService extends CrudService<BookLoan,Long>{

    void loanBook(Long bookId);

    void returnBook(Long bookLoanId);
}
