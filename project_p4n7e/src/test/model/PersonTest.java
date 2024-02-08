package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonTest {
    private Person testPerson;
    private Person emptyPerson;

    @BeforeEach
    void runBefore(){

        emptyPerson = new Person();testPerson = new Person("Cathy", "Canadian",30,"Female");
    }

    @Test
    void testEmptyConstructor(){

      assertEquals(0.0,testPerson.getRating());
      assertEquals("No comment yet",testPerson.getComment());
    }

    @Test
    void testParameterConstructor(){
        assertEquals("Cathy",testPerson.getName());
        assertEquals("Canadian",testPerson.getNationality());
        assertEquals(30,testPerson.getAge());
        assertEquals("Female",testPerson.getGender());
    }

    @Test
    void testGetRating(){
            testPerson.setRating(5.5);
            assertEquals(5.5,testPerson.getRating());
    }

    @Test
    void testGetComment(){
        testPerson.setComment("She is a great person!");
        assertEquals("She is a great person!",testPerson.getComment());
    }

    @Test
    void testToString(){
        assertTrue( testPerson.toString().contains("Name: Cathy, Nationality: Canadian, Age: 30, Gender: Female, Rating: 0.0, Comment: No comment yet"));
    }

    @Test
    void testSetName() {
        testPerson.setName("Cat");
        assertEquals("Cat",testPerson.getName());
    }

    @Test
    void testSetAge() {
        testPerson.setAge(18);
        assertEquals(18,testPerson.getAge());

    }

    @Test
    void testSetGenger() {
        testPerson.setGender("Male");
        assertEquals("Male",testPerson.getGender());

    }
    @Test
    void testSetNationality() {
        testPerson.setNationality("African");
        assertEquals("African",testPerson.getNationality());
    }
}