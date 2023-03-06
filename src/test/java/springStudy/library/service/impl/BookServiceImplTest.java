package springStudy.library.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import springStudy.library.model.Book;
import springStudy.library.repository.AuthorRepository;
import springStudy.library.repository.BookRepository;
import springStudy.library.service.AuthorService;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceImplTest {

    BookServiceImpl bookService;

    @Mock
    BookRepository bookRepository;
    @Mock
    AuthorRepository authorRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        bookService = new BookServiceImpl(bookRepository, authorRepository);
    }

    @Test
    void findAll() {

        Book book = new Book();
        HashSet bookData = new HashSet();
        bookData.add(book);

        when(bookRepository.findAll()).thenReturn(bookData);

        HashSet books = (HashSet) bookService.findAll();

        assertEquals(books.size(), 1);
        verify(bookRepository, times(1)).findAll();
    }
}