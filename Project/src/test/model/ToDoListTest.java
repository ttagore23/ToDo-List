package model;

import exceptions.IllegalInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ToDoListTest {
    ToDoList todolist;
    Task task;
    Task task1;
    Task task2;
    Task task3;

    @BeforeEach
    public void runBefore() { todolist = new ToDoList(); }

    @Test
     public void testConstructor() {
        assertEquals(0, todolist.size());
        assertEquals(0, todolist.listOfCompletedTasks.size());
        assertFalse(todolist.contains(task));
        assertFalse(todolist.listOfCompletedTasks.contains(task));
    }

    @Test
    public void testAddTask() {
        task1 = new Task("10:00", "I have a dentist appointment","27/07/20");
        task2 = new Task("3:00", "I have to go play basketball", "24/09/21");
        task3 = new Task("7:00", "I have to get a massage", "21/09/20");

        assertFalse(todolist.contains(task1));
        assertFalse(todolist.listOfCompletedTasks.contains(task1));
        assertEquals(0,todolist.size());
        assertEquals(0,todolist.listOfCompletedTasks.size());
        todolist.addTask(task1);
        assertTrue(todolist.contains(task1));
        assertFalse(todolist.contains(task2));
        assertFalse(todolist.listOfCompletedTasks.contains(task1));
        assertEquals(1,todolist.size());
        assertEquals(0, todolist.listOfCompletedTasks.size());
        todolist.addTask(task2);
        todolist.addTask(task3);
        assertTrue(todolist.contains(task3));
        assertFalse(todolist.listOfCompletedTasks.contains(task3));
        assertEquals(3,todolist.size());
        assertEquals(0,todolist.listOfCompletedTasks.size());
    }

    @Test
    public void testRemoveTask() {
        task1 = new Task("10:00", "I have a dentist appointment","27/07/20");
        task2 = new Task("3:00", "I have to go play basketball", "24/09/21");
        task3 = new Task("7:00", "I have to get a massage", "21/09/20");

        assertFalse(todolist.contains(task1));
        assertFalse(todolist.listOfCompletedTasks.contains(task1));
        assertEquals(0,todolist.size());
        assertEquals(0,todolist.listOfCompletedTasks.size());
        todolist.addTask(task1);
        todolist.removeTask(0);
        assertFalse(todolist.contains(task1));
        assertFalse(todolist.listOfCompletedTasks.contains(task1));
        assertEquals(0,todolist.size());
        assertEquals(0,todolist.listOfCompletedTasks.size());
        todolist.addTask(task2);
        todolist.addTask(task3);
        todolist.removeTask(1);
        assertTrue(todolist.contains(task2));
        assertFalse(todolist.contains(task3));
        assertFalse(todolist.listOfCompletedTasks.contains(task2));
        assertFalse(todolist.listOfCompletedTasks.contains(task3));
        assertEquals(1,todolist.size());
        assertEquals(0,todolist.listOfCompletedTasks.size());
    }

    @Test
    public void testMarkTaskAsCompleted() {
        task1 = new Task("10:00", "I have a dentist appointment","27/07/20");
        task2 = new Task("3:00", "I have to go play basketball", "24/09/21");
        task3 = new Task("7:00", "I have to get a massage", "21/09/20");

        assertFalse(todolist.contains(task1));
        assertFalse(todolist.listOfCompletedTasks.contains(task1));
        assertEquals(0,todolist.size());
        assertEquals(0,todolist.listOfCompletedTasks.size());
        todolist.addTask(task1);
        todolist.markTaskAsCompleted(0);
        assertEquals(todolist.getListOfTasks().size(), 0);
        assertEquals(todolist.getListOfCompletedTasks().size(),1);
        assertFalse(todolist.contains(task1));
        assertTrue(todolist.listOfCompletedTasks.contains(task1));
        assertEquals(0,todolist.size());
        assertEquals(1,todolist.listOfCompletedTasks.size());
        todolist.addTask(task2);
        todolist.addTask(task3);
        todolist.markTaskAsCompleted(1);
        assertEquals(todolist.getListOfTasks().size(), 1);
        assertEquals(todolist.getListOfCompletedTasks().size(),2);
        assertTrue(todolist.contains(task2));
        assertTrue(todolist.listOfCompletedTasks.contains(task3));
        assertFalse(todolist.listOfCompletedTasks.contains(task2));
        assertEquals(1,todolist.size());
        assertEquals(2,todolist.listOfCompletedTasks.size());
    }

    @Test
    public void testModifyTask() {
        task1 = new Task("10:00", "I have a dentist appointment","27/07/20");
        task2 = new Task("3:00", "I have to go play basketball", "24/09/21");
        task3 = new Task("7:00", "I have to get a massage", "21/09/20");

        assertFalse(todolist.contains(task1));
        assertFalse(todolist.listOfCompletedTasks.contains(task1));
        assertEquals(0,todolist.size());
        assertEquals(0,todolist.listOfCompletedTasks.size());
        todolist.addTask(task1);
        todolist.modifyTask(0, "12:00", "I have to get a haircut","01/08/20");
        assertEquals("12:00", task1.getTime());
        assertEquals("I have to get a haircut", task1.getDescription());
        assertEquals("01/08/20", task1.getDate());
        assertTrue(todolist.contains(task1));
        assertFalse(todolist.listOfCompletedTasks.contains(task1));
        assertEquals(1,todolist.size());
        assertEquals(0,todolist.listOfCompletedTasks.size());
        todolist.addTask(task2);
        todolist.addTask(task3);
        todolist.modifyTask(2, "1:00","I have to get a shave","23/09/20");
        assertEquals("1:00", task3.getTime());
        assertEquals("I have to get a shave", task3.getDescription());
        assertEquals("23/09/20", task3.getDate());
        assertTrue(todolist.contains(task1));
        assertTrue(todolist.contains(task2));
        assertTrue(todolist.contains(task3));
        assertFalse(todolist.listOfCompletedTasks.contains(task1));
        assertFalse(todolist.listOfCompletedTasks.contains(task2));
        assertFalse(todolist.listOfCompletedTasks.contains(task3));
        assertEquals(3,todolist.size());
        assertEquals(0,todolist.listOfCompletedTasks.size());
    }

    @Test
    public void testValidateIndex() {
        task1 = new Task("10:00", "I have a dentist appointment","27/07/20");
        task2 = new Task("3:00", "I have to go play basketball", "24/09/21");
        task3 = new Task("7:00", "I have to get a massage", "21/09/20");

        try {
            todolist.validateIndex(-1);
        } catch (IllegalInputException e) {
            e.printStackTrace();
        }

        try {
            todolist.validateIndex(1);
        } catch (IllegalInputException e) {
            fail();
        }
    }

    @Test
    public void testContains() {
        task1 = new Task("10:00", "I have a dentist appointment","27/07/20");
        task2 = new Task("3:00", "I have to go play basketball", "24/09/21");
        task3 = new Task("7:00", "I have to get a massage", "21/09/20");

        assertFalse(todolist.contains(task));
        assertFalse(todolist.listOfCompletedTasks.contains(task));
        todolist.addTask(task1);
        todolist.addTask(task2);
        assertTrue(todolist.contains(task1));
        assertTrue(todolist.contains(task2));
        assertFalse(todolist.contains(task3));
        assertFalse(todolist.listOfCompletedTasks.contains(task));
        assertFalse(todolist.listOfCompletedTasks.contains(task3));
    }

    @Test
    public void testSize() {
        task1 = new Task("10:00", "I have a dentist appointment","27/07/20");
        task2 = new Task("3:00", "I have to go play basketball", "24/09/21");
        task3 = new Task("7:00", "I have to get a massage", "21/09/20");

        assertEquals(0,todolist.size());
        assertEquals(0,todolist.listOfCompletedTasks.size());
        todolist.addTask(task);
        todolist.addTask(task1);
        todolist.addTask(task2);
        todolist.addTask(task3);
        assertEquals(4,todolist.size());
        assertEquals(0,todolist.listOfCompletedTasks.size());
    }
}
