package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    Task task;

    @Test
    public void testConstructor() {
        task = new Task("10:00", "I have a dentist appointment","27/07/20");

        assertEquals(task.getDescription(),"I have a dentist appointment");
        assertEquals(task.getDate(),"27/07/20");
        assertEquals(task.getTime(),"10:00");
    }

}
