package springStudy.library.service.impl;

import org.springframework.stereotype.Service;
import springStudy.library.exception.CannotDeleteForeignKeyException;
import springStudy.library.model.Author;
import springStudy.library.repository.AuthorRepository;
import springStudy.library.repository.BookRepository;
import springStudy.library.service.AuthorService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {

        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }
    @Override
    public Set<Author> findAll() {
        Set<Author> allAuthors = new HashSet<>();
        authorRepository.findAll().iterator().forEachRemaining(allAuthors::add);

        return allAuthors;
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).get();
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void delete(Author author) {
        //TODO - Check for book
        authorRepository.delete(author);
    }

    @Override
    public void deleteById(Long id) throws CannotDeleteForeignKeyException {

        Optional<Author> authorOptional = authorRepository.findById(id);
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            boolean hasAssociatedBooks = StreamSupport.stream(bookRepository.findAll().spliterator(), false)
                    .anyMatch(book -> book.getAuthors().contains(author));
            if (hasAssociatedBooks) {
                throw new CannotDeleteForeignKeyException("Cannot delete author with associated books.");
            }else{
                authorRepository.deleteById(id);
            }
        } else {
            throw new RuntimeException("Author not found.");
        }
    }
}
