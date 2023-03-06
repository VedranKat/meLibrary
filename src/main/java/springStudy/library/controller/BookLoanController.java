package springStudy.library.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springStudy.library.dto.BookDto;
import springStudy.library.exception.CannotDeleteForeignKeyException;
import springStudy.library.model.Book;
import springStudy.library.model.BookLoan;
import springStudy.library.service.BookLoanService;
import springStudy.library.service.BookService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequestMapping("/v1/bookLoans")
@Controller
public class BookLoanController {

    private final BookLoanService bookLoanService;
    private final BookService bookService;

    public BookLoanController(BookLoanService bookLoanService, BookService bookService) {
        this.bookLoanService = bookLoanService;
        this.bookService = bookService;
    }

    @GetMapping("")
    public String getBookLoans(Model model){

        model.addAttribute("bookLoans", bookLoanService.findAll());
        return "bookLoans/bookLoanList";
    }

    @GetMapping("/loan")
    public String loanBook(Model model){

        model.addAttribute("books", bookService.findAll());
        model.addAttribute("bookLoan", BookLoan.builder().build());

        return "bookLoans/loanBook";
    }

    @PostMapping("")
    public String addLoan(@RequestParam("book") Long bookId){

        bookLoanService.loanBook(bookId);

        return "redirect:/v1/bookLoans";
    }

    @GetMapping("/return/{bookLoanId}")
    public String returnBook(@PathVariable("bookLoanId") String bookLoanId){

        bookLoanService.returnBook(Long.valueOf(bookLoanId));

        return "redirect:/v1/bookLoans";
    }

    @GetMapping("/delete/{bookLoanId}")
    public String deleteBookLoan(@PathVariable("bookLoanId") String bookLoanId) throws CannotDeleteForeignKeyException {

        bookLoanService.deleteById(Long.valueOf(bookLoanId));

        return "redirect:/v1/bookLoans";
    }


}
