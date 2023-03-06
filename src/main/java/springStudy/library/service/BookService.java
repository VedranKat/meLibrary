package springStudy.library.service;

import springStudy.library.model.Book;

import java.util.Set;

public interface BookService extends CrudService<Book, Long>{

    void removeAuthorFromBook(Long bookId, Long authorId);

    void addAuthorToBook(Long bookId, Long authorId);
}
