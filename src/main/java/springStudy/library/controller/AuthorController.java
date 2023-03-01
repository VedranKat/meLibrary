package springStudy.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springStudy.library.exception.CannotDeleteForeignKeyException;
import springStudy.library.model.Author;
import springStudy.library.service.AuthorService;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping("")
    public String getAuthors(Model model){

        model.addAttribute("authors", authorService.findAll());

        return "authors/authList";

    }

    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable String id) throws CannotDeleteForeignKeyException {
        authorService.deleteById(Long.valueOf(id));

        return "redirect:/authors";
    }

    @GetMapping("/add")
    public String getNewAuthor(Model model){

        model.addAttribute("author", Author.builder().build());

        return "authors/addAuthor";
    }

    @PostMapping("/add")
    public String addNewAuthor(Author author){

        authorService.save(author);

        return "redirect:/authors";

    }
}
