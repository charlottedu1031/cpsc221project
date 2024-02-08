package persistence;

import model.Person;
import model.DatingDiary;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.
    // Code influced by the JsonSerizalizationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.
    @Test
    void testWriterInvalidFile() {
        try {
            DatingDiary dd = new DatingDiary("My dating diary");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            DatingDiary dd = new DatingDiary("My dating diary");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDatingDiary.json");
            writer.open();
            writer.write(dd);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyDatingDiary.json");
            dd = reader.read();
            assertEquals("My dating diary", dd.getOwnerName());
            assertEquals(0, dd.getPeople().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Person p1 = new Person("Cat", "Canadian",30,"Female");
            Person p2 = new Person("Raj", "Canadian",32,"Male");
            p1.setRating(5);
            p2.setComment("Handsome");
            DatingDiary dd = new DatingDiary("My dating diary");
            dd.addPerson(p1);
            dd.addPerson(p2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDatingDiary.json");
            writer.open();
            writer.write(dd);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralDatingDiary.json");
            dd = reader.read();
            assertEquals("My dating diary", dd.getOwnerName());
            List<Person> people = dd.getPeople();
            assertEquals(2, people.size());
            checkPerson(p1, people.get(0));
            checkPerson(p2, people.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}