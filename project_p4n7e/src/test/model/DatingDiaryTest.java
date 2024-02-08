package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DatingDiaryTest {
    private DatingDiary testDiary;
    private Person p1;
    private Person p2;
    private Person p3;

    @BeforeEach
    void runBefore(){
        testDiary = new DatingDiary("Char");
        p1 = new Person("Cat","Canadian",30,"Female");
        p2 = new Person("Rui","Chinese",32,"Female");
        p3 = new Person("Raj","Canadian",35,"Male");
    }

    @Test
    void testConstructor(){
        assertEquals("Char",testDiary.getOwnerName());
        assertTrue(testDiary.getPeople().isEmpty());
    }

    @Test
    void testAddOnePerson(){
        testDiary.addPerson(p1);
        List<Person> people = testDiary.getPeople();
        assertEquals(1,people.size());
        assertEquals(p1,people.get(0));

    }

    @Test
    void testAddMultiplePerson(){
        testDiary.addPerson(p1);
        testDiary.addPerson(p2);
        List<Person> people = testDiary.getPeople();
        assertEquals(2,people.size());
        assertEquals(p1,people.get(0));
        assertEquals(p2,people.get(1));
    }

    @Test
    void testViewNames(){
        testDiary.addPerson(p1);
        testDiary.addPerson(p2);
        testDiary.addPerson(p3);
        List<String> names = testDiary.viewNames();
        assertEquals(3,names.size());
        assertEquals("Cat",names.get(0));
        assertEquals("Rui",names.get(1));
        assertEquals("Raj",names.get(2));
    }

    @Test
    void testSelectAddCommentTrue(){
        testDiary.addPerson(p1);
        testDiary.addPerson(p2);
        testDiary.addPerson(p3);
        assertTrue(testDiary.selectAddComment("Rui","She is so lame!"));
        //assertEquals("She is awesome!",);?

    }

    @Test
    void testSelectAddCommentFalse(){
        testDiary.addPerson(p1);
        testDiary.addPerson(p2);
        testDiary.addPerson(p3);
        assertFalse(testDiary.selectAddComment("Mona","Average"));
    }

    @Test
    void testSelectAddRateTrue(){
        testDiary.addPerson(p1);
        testDiary.addPerson(p2);
        testDiary.addPerson(p3);
        assertTrue(testDiary.selectAddRate("Rui",0));
        //assertEquals("She is awesome!",);?
    }

    @Test
    void testSelectAddRateFalse(){
        testDiary.addPerson(p1);
        testDiary.addPerson(p2);
        testDiary.addPerson(p3);
        assertFalse(testDiary.selectAddRate("Mona",5));
    }

    @Test
    void testselectPersonByNameExist(){
        testDiary.addPerson(p1);
        testDiary.addPerson(p2);
        Person p = testDiary.selectPersonByName("Cat");
        assertEquals("Cat",p.getName());
        assertEquals("Canadian",p.getNationality());
        assertEquals(30,p.getAge());
        assertEquals("Female",p.getGender());
    }

    @Test
    void testselectPersonByNameNoExist(){
        testDiary.addPerson(p1);
        testDiary.addPerson(p2);
        Person p = testDiary.selectPersonByName("Iris");
        assertNull(p);
    }
}
