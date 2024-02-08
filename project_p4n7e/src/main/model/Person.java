package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a person having a name, nationality,age,gender. Rating and comment if applicable.
public class Person implements Writable {
    private String name;
    private String nationality;
    private int age;
    private String gender;
    private double rating;
    private String comment;

    //EFEECTS:constructs a person with no fields assigned.
    public Person() {

    }

    // REQUIRES: all passing parameters have a non-zero length, age >0,.
    // EFFECTS: constructs a person with name,nationality,age, gender with no comment and -100 rating by default.
    public Person(String name, String nationality, int age, String gender) {
        this.name = name;
        this.nationality = nationality;
        this.age = age;
        this.gender = gender;
        comment = "No comment yet";
        rating = 0.0;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    //REQUIRES: rating is in range of -10.0 to 10.0.
    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;

    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public double getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    //EFFECTS: returns string representation of this person.
    @Override
    public String toString() {
        return  "Name: " + name
                + ", Nationality: " + nationality
                + ", Age: " + age
                + ", Gender: " + gender
                + ", Rating: " + rating
                + ", Comment: " + comment
                ;
    }

    // EFFECTS: returns this person as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("nationality", nationality);
        json.put("rating", rating);
        json.put("age", age);
        json.put("gender", gender);
        json.put("comment", comment);
        return json;
    }
}
