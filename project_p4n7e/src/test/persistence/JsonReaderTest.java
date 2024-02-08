package persistence;

import model.Person;
import model.DatingDiary;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Code influced by the JsonSerizalizationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            DatingDiary dd = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyDatingDiary() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyDatingDiary.json");
        try {
            DatingDiary dd = reader.read();
            assertEquals("My dating diary", dd.getOwnerName());
            assertEquals(0, dd.getPeople().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralDatingDiary() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralDatingDiary.json");
        try {
            Person p1 = new Person("Mona", "Chinese",29,"Female");
            Person p2 = new Person("Daryna", "Canadian",32,"Male");
            DatingDiary dd = reader.read();
            assertEquals("My dating diary", dd.getOwnerName());
            List<Person> people = dd.getPeople();
            assertEquals(2, people.size());
            checkPerson(p1, people.get(0));
            checkPerson(p2, people.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}