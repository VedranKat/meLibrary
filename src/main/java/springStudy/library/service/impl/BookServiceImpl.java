package springStudy.library.service.impl;

import org.springframework.stereotype.Service;
import springStudy.library.exception.BookNotFoundException;
import springStudy.library.model.Book;
import springStudy.library.repository.AuthorRepository;
import springStudy.library.repository.BookRepository;
import springStudy.library.service.AuthorService;
import springStudy.library.service.BookService;

import java.util.HashSet;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository,AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public Set findAll() {
        Set<Book> allBooks = new HashSet<>();
        bookRepository.findAll().iterator().forEachRemaining(allBooks::add);

        return allBooks;
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public Book save(Book book) { return bookRepository.save(book); }

    @Override
    public void delete(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void removeAuthorFromBook(Long bookId, Long authorId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        book.getAuthors().removeIf(author -> author.getId().equals(authorId));
        bookRepository.save(book);
    }

    @Override
    public void addAuthorToBook(Long bookId, Long authorId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        book.getAuthors().add(authorRepository.findById(authorId).get());
        bookRepository.save(book);
    }

}
