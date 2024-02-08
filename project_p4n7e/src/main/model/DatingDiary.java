package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//Represents a dating diary one could have, include owner name and a list of people they have added.
public class DatingDiary implements Writable {
    private String ownerName;
    private List<Person> people;

    //EFFECTS: constructs a diary with an ownername, and an empty list of people.
    public DatingDiary(String ownerName) {
        this.ownerName = ownerName;
        people = new ArrayList<>();
    }

    // MODIFIES:this
    // EFEECTS: add person p to the end of my list of people, and log the event to eventlog.
    public void addPerson(Person p) {
        people.add(p);
        EventLog.getInstance().logEvent(new Event("A person added to the diary."));
    }

    // MODIFIES:this
    // EFEECTS: delete a person based on the index in my list of people, and log the event to eventlog.
    public void deletePerson(int index) {
        people.remove(index);
        EventLog.getInstance().logEvent(new Event("A person deleted from the diary."));
    }

    //EFFECTS: return a list of names from the list of people, in the order in which they were added.
    public List<String> viewNames() {
        List<String> names = new ArrayList<>();
        for (Person p : people) {
            names.add(p.getName());
        }
        return names;
    }

    //REQUIRES: name is a string and has a non-zero length
    //EFFECTS:look for this person by name in the  list of people, if found, return this person.If not,return null.
    public Person selectPersonByName(String name) {
        for (Person p : people) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    //REQUIRES: comment is string and has a non-zero length
    //MODIFIES:this
    // EFFECTS: look for this person by name in the list of
    // people, if found, add comment to this person and
    // return true.If not,return false.
    public boolean selectAddComment(String name, String comment) {
        boolean success = false;
        for (Person p : people) {
            if (p.getName().equals(name)) {
                p.setComment(comment);
                success = true;
            }
        }
        return success;
    }

    //REQUIRES: rating is in range of -10.0 to 10.0.
    //MODIFIES:this
    // EFFECTS: locate this person by name in the list of
    // people, if found, rate this person and
    // return true. If not,return false.
    public boolean selectAddRate(String name, double rating) {
        boolean success = false;
        for (Person p : people) {
            if (p.getName().equals(name)) {
                p.setRating(rating);
                success = true;
            }
        }
        return success;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public List<Person> getPeople() {
        return people;
    }

    //EFFECTS: returns this daitng diary as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Owner Name", ownerName);
        json.put("People", personToJson());
        return json;
    }

    // EFFECTS: returns people in this datingdiary as a JSON array
    private JSONArray personToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Person p : people) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }
}

