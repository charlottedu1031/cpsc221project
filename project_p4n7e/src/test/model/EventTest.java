package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

// Code influced by the AlarmSystem https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.
public class EventTest {
	private Event event1;
	private Event event2;
	private Date d;

	//NOTE: these tests might fail if time at which line (2) below is executed
	//is different from time that line (1) is executed.  Lines (1) and (2) must
	//run in same millisecond for this test to make sense and pass.

	@BeforeEach
	public void runBefore() {
		event1 = new Event("A person added"); // (1)
		d = Calendar.getInstance().getTime(); // (2)
		event2 = new Event("A person deleted");
	}
	
	@Test
	public void testEvent() {
		assertEquals("A person added", event1.getDescription());
		assertEquals(d, event1.getDate());
	}

	@Test
	void testEqualsObject() {
		assertFalse(event1.equals(event2));
		assertTrue(event1.equals(event1));
		assertFalse(event1.equals(d));
		event2 = null;
		assertFalse(event1.equals(event2));
	}

	@Test
	void testHashCode() {
		assertTrue(event1.hashCode() == event1.hashCode());
		assertFalse(event1.hashCode() == event2.hashCode());
	}

	@Test
	public void testToString() {
		assertEquals(d.toString() + "\n" + "A person added", event1.toString());
	}
}
