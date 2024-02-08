package model;

import java.util.Calendar;
import java.util.Date;


/**
 * Represents an alarm system event.
 */
// Code copied from the AlarmSystem https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.
public class Event {
    private static final int HASH_CONSTANT = 13;
    private Date dateLogged;
    private String description;


    //EFFECTS: Creates an event with the given description and the current date time stamp.
    public Event(String description) {
        dateLogged = Calendar.getInstance().getTime();
        this.description = description;
    }

    public Date getDate() {
        return dateLogged;
    }

    public String getDescription() {
        return description;
    }

    //EFFEECTS: returns true if two events have the same date and description,otherwise,false.
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other.getClass() != this.getClass()) {
            return false;
        }

        Event otherEvent = (Event) other;

        return (this.dateLogged.equals(otherEvent.dateLogged)
                && this.description.equals(otherEvent.description));
    }

    //EFFECTS: return hashcode(int) of this event
    @Override
    public int hashCode() {
        return (HASH_CONSTANT * dateLogged.hashCode() + description.hashCode());
    }

    //EFFECTS:returns string representation of this event.
    @Override
    public String toString() {
        return dateLogged.toString() + "\n" + description;
    }
}
