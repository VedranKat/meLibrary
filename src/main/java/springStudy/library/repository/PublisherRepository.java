package springStudy.library.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import springStudy.library.model.Publisher;

import java.util.Optional;

@Repository
public interface PublisherRepository extends CrudRepository<Publisher, Long> {

    Optional<Publisher> findByName(String name);
}
