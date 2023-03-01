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
import springStudy.library.service.BookService;
import springStudy.library.service.PublisherService;
import springStudy.library.util.CustomMetrics;

@RequestMapping("/v1/books")
@Controller
public class BookController {

    private final BookService bookService;
    private final PublisherService publisherService;
    private final CustomMetrics customMetrics;

    Logger logger = LoggerFactory.getLogger(BookController.class);

    public BookController(BookService bookService, PublisherService publisherService, CustomMetrics customMetrics) {
        logger.info("Constructor called");
        this.bookService = bookService;
        this.publisherService = publisherService;
        this.customMetrics = customMetrics;
    }

    @GetMapping("")
    public String getBooks(Model model){

        logger.info("Fetching all books");

        model.addAttribute("books", bookService.findAll());


        return "books/bookList";
    }

    /*
    Get request because delete requires a script and authorization for js
     */
    @GetMapping("/delete/{id}")
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
}
