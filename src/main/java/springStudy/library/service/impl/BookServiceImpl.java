package springStudy.library.service.impl;

import org.springframework.stereotype.Service;
import springStudy.library.model.Book;
import springStudy.library.repository.BookRepository;
import springStudy.library.service.BookService;

import java.util.HashSet;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository1) {
        this.bookRepository = bookRepository1;
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
    public Book save(Book book) {

        return bookRepository.save(book);

    }

    @Override
    public void delete(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
