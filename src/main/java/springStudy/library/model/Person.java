package springStudy.library.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class Person {
    //TODO if I add customer and more humanoid entities along with Author, make them all extend this
}
