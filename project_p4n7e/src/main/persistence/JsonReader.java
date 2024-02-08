package persistence;

import model.DatingDiary;
import model.Person;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads datingdiary from JSON data stored in file.
// Code influced by the JsonSerizalizationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads datingdiary from file and returns it;
    // throws IOException if an error occurs reading data from file
    public DatingDiary read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDatingDiary(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses datingdiary from JSON object and returns it
    public DatingDiary parseDatingDiary(JSONObject jsonObject) {
        String ownerName = jsonObject.getString("Owner Name");
        DatingDiary dd = new DatingDiary(ownerName);
        addPeople(dd, jsonObject);
        return dd;
    }

    // MODIFIES: dd
    // EFFECTS: parses peron from JSON object and adds them to datingdiary
    private void addPeople(DatingDiary dd, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("People");//?
        for (Object json : jsonArray) {
            JSONObject nextPerson = (JSONObject) json;
            addPerson(dd, nextPerson);
        }
    }

    // MODIFIES: dd
    // EFFECTS: parses person from JSON object and adds it to datingdiary
    private void addPerson(DatingDiary dd, JSONObject jsonObject) {
        String nationality = jsonObject.getString("nationality");
        String gender = jsonObject.getString("gender");
        String name = jsonObject.getString("name");
        Double rating = jsonObject.getDouble("rating");
        String comment = jsonObject.getString("comment");
        int age = jsonObject.getInt("age");
        Person person = new Person(name, nationality, age, gender);
        person.setRating(rating);
        person.setComment(comment);
        dd.addPerson(person);
    }
}
