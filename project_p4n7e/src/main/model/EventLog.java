package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Represents a log of alarm system events.
 * We use the Singleton Design Pattern to ensure that there is only
 * one EventLog in the system and that the system has global access
 * to the single instance of the EventLog.
 */
// Code copied from the AlarmSystem https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.
public class EventLog implements Iterable<Event> {
    private static EventLog theLog;
    private Collection<Event> events;

    //EFFECTS: Creates an eventlog with an empty list of events.
    private EventLog() {
        events = new ArrayList<Event>();
    }

    //MODIFIES: this
   //EFFECTS: creates instance of EventLog if it doesn't already exist.And return instance of EventLog
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }

        return theLog;
    }

    //MODIFIES: this
   //EFFECTS: Adds an event to the event log.
    public void logEvent(Event e) {
        events.add(e);
    }

    //EFFECTS: return the iterator of the events list
    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}
