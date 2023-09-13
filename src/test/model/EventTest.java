package model;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Event class
 */
public class EventTest {
    private Event event;
    private Date date;

    // NOTE: these tests might fail if time at which line (2) below is executed
    // is different from time that line (1) is executed. Lines (1) and (2) must
    // run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        event = new Event("Sensor open at door"); // (1)
        date = Calendar.getInstance().getTime(); // (2)
    }

    @Test
    public void testEvent() {
        assertEquals("Sensor open at door", event.getDescription());
        assertEquals(date, event.getDate());
    }

    @Test
    public void testToString() {
        assertEquals(date.toString() + "\n" + "Sensor open at door", event.toString());
    }

    @Test
    public void testEquals() {
        Event event1 = new Event("Sensor open at door");
        Event event2 = new Event("Sensor open at door");
        assertEquals(event1, event2);
        assertNotEquals(null, event1);
        assertNotEquals(event1, new Object());
    }

    @Test
    public void testHashCode() {
        Event event1 = new Event("Sensor open at door");
        Event event2 = new Event("Sensor open at door");
        assertEquals(event1.hashCode(), event2.hashCode());
    }
}
