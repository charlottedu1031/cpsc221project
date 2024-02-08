package persistence;

import model.Person;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkPerson(Person p1, Person p2) {
        assertEquals(p1.getName(), p2.getName());
        assertEquals(p1.getNationality(), p2.getNationality());
        assertEquals(p1.getAge(), p2.getAge());
        assertEquals(p1.getGender(), p2.getGender());
        assertEquals(p1.getRating(),p2.getRating());
        assertEquals(p1.getComment(),p2.getComment());
    }
}
