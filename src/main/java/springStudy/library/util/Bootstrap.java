package springStudy.library.util;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import springStudy.library.model.Author;
import springStudy.library.model.Book;
import springStudy.library.model.Publisher;
import springStudy.library.repository.AuthorRepository;
import springStudy.library.repository.BookRepository;
import springStudy.library.repository.PublisherRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public Bootstrap(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        bookRepository.saveAll(getData());

    }

    private List<Book> getData() {

        List<Book> books = new ArrayList<>();

        //Optional
        Optional<Book> bookOptional1 = bookRepository.findByTitle("Spring in Action");
        Optional<Book> bookOptional2 = bookRepository.findByTitle("Spring Start Here");

        Optional<Author> authorOptional1 = authorRepository.findByNameSurname("John Thompson");
        Optional<Author> authorOptional2 = authorRepository.findByNameSurname("Frane Thompson");

        Optional<Publisher> publisherOptional1 = publisherRepository.findByName("Manning");

        //Entities
        Book book1 = bookOptional1.get();
        Book book2 = bookOptional2.get();

        Author author1 = authorOptional1.get();
        Author author2 = authorOptional2.get();

        Publisher publisher1 = publisherOptional1.get();

        //Set authors
        book1.getAuthors().add(author1);
        book2.getAuthors().add(author2);

        //Set publishers
        book1.setPublisher(publisher1);
        book2.setPublisher(publisher1);

        //Add to list
        books.add(book1);
        books.add(book2);

        return books;
    }
}
