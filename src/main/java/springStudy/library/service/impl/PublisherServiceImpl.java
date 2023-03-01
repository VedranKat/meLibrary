package springStudy.library.service.impl;

import org.springframework.stereotype.Service;
import springStudy.library.exception.CannotDeleteForeignKeyException;
import springStudy.library.model.Publisher;
import springStudy.library.repository.BookRepository;
import springStudy.library.repository.PublisherRepository;
import springStudy.library.service.PublisherService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.StreamSupport;

@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;
    private final BookRepository bookRepository;

    public PublisherServiceImpl(PublisherRepository publisherRepository, BookRepository bookRepository) {
        this.publisherRepository = publisherRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Set<Publisher> findAll() {
        Set<Publisher> allPublishers = new HashSet<>();
        publisherRepository.findAll().iterator().forEachRemaining(allPublishers::add);

        return allPublishers;
    }

    @Override
    public Publisher findById(Long id) {
        return publisherRepository.findById(id).get();
    }

    @Override
    public Publisher save(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @Override
    public void delete(Publisher publisher) {
        //TODO - Check for book
        publisherRepository.delete(publisher);
    }

    @Override
    public void deleteById(Long id) throws CannotDeleteForeignKeyException {

        Optional<Publisher> publisherOptional = publisherRepository.findById(id);
        if (publisherOptional.isPresent()) {
            Publisher publisher = publisherOptional.get();
            boolean hasAssociatedBooks = StreamSupport.stream(bookRepository.findAll().spliterator(), false)
                    .anyMatch(book -> publisher.equals(book.getPublisher()));
            if (hasAssociatedBooks) {
                throw new CannotDeleteForeignKeyException("Cannot delete publisher with associated books.");
            } else {
                publisherRepository.deleteById(id);
            }
        } else {
            throw new RuntimeException("Publisher not found.");
        }

        // .anyMatch(book -> book.getPublisher().equals(publisher)); !!!!throws null exception!!!


    }
}
