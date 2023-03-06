package springStudy.library.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springStudy.library.dto.BookDto;
import springStudy.library.dto.mapper.BookMapper;
import springStudy.library.exception.CannotDeleteForeignKeyException;
import springStudy.library.model.Book;
import springStudy.library.service.AuthorService;
import springStudy.library.service.BookLoanService;
import springStudy.library.service.BookService;
import springStudy.library.service.PublisherService;
import springStudy.library.util.CustomMetrics;

@RequestMapping("/v1/books")
@Controller
public class BookController {

    private final BookService bookService;
    private final PublisherService publisherService;
    private final CustomMetrics customMetrics;
    private final AuthorService authorService;

    Logger logger = LoggerFactory.getLogger(BookController.class);

    public BookController(BookService bookService, PublisherService publisherService, CustomMetrics customMetrics,
                          AuthorService authorService) {
        logger.info("Constructor called");
        this.bookService = bookService;
        this.publisherService = publisherService;
        this.customMetrics = customMetrics;
        this.authorService = authorService;
    }

    @GetMapping("")
    public String getBooks(Model model){

        logger.info("Fetching all books");

        model.addAttribute("books", bookService.findAll());


        return "books/bookList";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable String id) throws CannotDeleteForeignKeyException {

        bookService.deleteById(Long.valueOf(id));

        return "redirect:/v1/books";
    }

    @GetMapping("/add")
    public String getNewBook(Model model){

        model.addAttribute("publishers", publisherService.findAll());
        model.addAttribute("bookDto", BookDto.builder().build());

        return "books/addBook";
    }

    @PostMapping("")
    public String addNewBook(@Valid BookDto bookDto){

        bookService.save(BookMapper.INSTANCE.bookDtoToBook(bookDto));

        customMetrics.incrementCounter();

        return "redirect:/v1/books";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable String id, Model model){

        Book book = bookService.findById(Long.valueOf(id));

        model.addAttribute("bookDto", BookMapper.INSTANCE.bookToBookDto(book));
        logger.info(book.getBookLoans().isEmpty() + " " + book.getBookLoans().size());
        model.addAttribute("publishers", publisherService.findAll());

        return "books/editBook";
    }

    @GetMapping("/{id}/authors")
    public String getBookAuthors(@PathVariable String id, Model model){

        Book book = bookService.findById(Long.valueOf(id));

        model.addAttribute("book", BookMapper.INSTANCE.bookToBookDto(book));
        model.addAttribute("allAuthors", authorService.findAll()); //change to dto later

        return "books/bookAuthorList";
    }

    @GetMapping("/{id}/authors/delete/{authorId}")
    public String deleteBookAuthor(@PathVariable String id, @PathVariable String authorId){

        bookService.removeAuthorFromBook(Long.valueOf(id), Long.valueOf(authorId));

        return "redirect:/v1/books/" + id + "/authors";
    }

    @GetMapping("/{id}/authors/add/")
    public String addBookAuthor(@PathVariable String id, @RequestParam String authorId){

        bookService.addAuthorToBook(Long.valueOf(id), Long.valueOf(authorId));

        return "redirect:/v1/books/" + id + "/authors";
    }



}
