package springStudy.library.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PublisherTest {

    Publisher publisher;

    @BeforeEach
    public void setUp() {

        publisher = new Publisher();
    }

    @Test
    void getId() {

        Long idvalue = 4L;
        publisher.setId(idvalue);
        assertEquals(4l, publisher.getId());
    }

    @Test
    void getName() {

        String name = "Test";
        publisher.setName(name);
        assertEquals(name, publisher.getName());
    }

    @Test
    void getAddress() {

        String address = "Test";
        publisher.setAddress(address);
        assertEquals(address, publisher.getAddress());
    }

    @Test
    void getCity() {

        String city = "Test";
        publisher.setCity(city);
        assertEquals(city, publisher.getCity());
    }

    @Test
    void getCountry() {

        String country = "Test";
        publisher.setCountry(country);
        assertEquals(country, publisher.getCountry());
    }
}