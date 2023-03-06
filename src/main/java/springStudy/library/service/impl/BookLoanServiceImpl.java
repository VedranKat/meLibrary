package springStudy.library.service.impl;

import org.springframework.stereotype.Service;
import springStudy.library.exception.CannotDeleteForeignKeyException;
import springStudy.library.model.Book;
import springStudy.library.model.BookLoan;
import springStudy.library.repository.BookLoanRepository;
import springStudy.library.repository.BookRepository;
import springStudy.library.service.BookLoanService;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class BookLoanServiceImpl implements BookLoanService {

    private final BookLoanRepository bookLoanRepository;
    private final BookRepository bookRepository;


    public BookLoanServiceImpl(BookLoanRepository bookLoanRepository,
                               BookRepository bookRepository) {
        this.bookLoanRepository = bookLoanRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Set<BookLoan> findAll() {
        Set<BookLoan> allBookLoans = new HashSet<>();
        bookLoanRepository.findAll().iterator().forEachRemaining(allBookLoans::add);

        return allBookLoans;
    }

    @Override
    public BookLoan findById(Long aLong) {

        return bookLoanRepository.findById(aLong).get();

    }

    @Override
    public BookLoan save(BookLoan object) {

        return bookLoanRepository.save(object);
    }

    @Override
    public void delete(BookLoan object) {

        bookLoanRepository.delete(object);

    }

    @Override
    public void deleteById(Long aLong) throws CannotDeleteForeignKeyException {

        Optional<BookLoan> optionalBookLoan = bookLoanRepository.findById(aLong);
        if (optionalBookLoan.isPresent()) {
            BookLoan bookLoan = optionalBookLoan.get();
            if (bookLoan.getReturnDate() != null) {
                bookLoanRepository.deleteById(aLong);
            } else {
                // handle the case when the return date is null
            }
        } else {
            // handle the case when the book loan is not found
        }
    }

    @Override
    public void loanBook(Long bookId) {

        Date loanDate = new Date();

        BookLoan bookLoan = new BookLoan();
        bookLoan.setLoanDate(loanDate);
        bookLoan.setUsername("default user");

        Book book = bookRepository.findById(Long.valueOf(bookId)).get();

        if(book.getCopies() > 0){
            bookLoan.setBook(book);
            book.setCopies(book.getCopies() - 1);
            bookLoan.setBook(book);
            this.save(bookLoan);
        } else {
            // handle the case when the book is not available
        }
    }

    @Override
    public void returnBook(Long bookLoanId) {

        Optional<BookLoan> optionalBookLoan = bookLoanRepository.findById(bookLoanId);
        if (optionalBookLoan.isPresent()) {
            BookLoan bookLoan = optionalBookLoan.get();

            Book book = bookLoan.getBook();
            book.setCopies(book.getCopies() + 1);
            bookRepository.save(book);

            bookLoan.setBook(book);
            bookLoan.setReturnDate(new Date());
            bookLoanRepository.save(bookLoan);
        } else {
            // handle the case when the book loan is not found
        }
    }
}
