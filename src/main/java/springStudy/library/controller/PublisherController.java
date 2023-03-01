package springStudy.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springStudy.library.exception.CannotDeleteForeignKeyException;
import springStudy.library.model.Publisher;
import springStudy.library.service.PublisherService;

@RequestMapping("/publishers")
@Controller
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @RequestMapping("")
    public String getBooks(Model model){

        model.addAttribute("publishers", publisherService.findAll());

        return "publishers/publisherList";
    }

    @GetMapping("/delete/{id}")
    public String deletePublisher(@PathVariable String id) throws CannotDeleteForeignKeyException {
        publisherService.deleteById(Long.valueOf(id));

        return "redirect:/publishers";
    }

    @GetMapping("/add")
    public String getNewPublisher(Model model){

        model.addAttribute("publisher", Publisher.builder().build());

        return "publishers/addPublisher";
    }

    @PostMapping("/add")
    public String addNewPublisher(Publisher publisher){

        publisherService.save(publisher);

        return "redirect:/publishers";

    }
}
