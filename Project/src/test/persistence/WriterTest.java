package persistence;

import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest {
    private Task task1;
    private Task task2;
    private ToDoList toDoList;

    @BeforeEach
    void runBefore() {
        task1 = new Task("1:00", "I have a date", "01/09/20");
        task2 = new Task("7:00", "I have to go out", "21/10/20");
        toDoList = new ToDoList();
    }

    @Test
    void testWriteTasks() {

        try {
            Writer testWriter = new Writer(new File("./data/tasks.txt"));
            testWriter.write(task1);
            testWriter.write(task2);
            testWriter.close();

        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            fail();
        }

        try {
            List<Task> tasks = Reader.readTasks(new File("./data/tasks.txt"));
            Task task1 = tasks.get(0);
            assertEquals("1:00", task1.getTime());
            assertEquals("I have a date", task1.getDescription());
            assertEquals("01/09/20", task1.getDate());

            Task task2 = tasks.get(1);
            assertEquals("7:00", task2.getTime());
            assertEquals("I have to go out", task2.getDescription());
            assertEquals("21/10/20", task2.getDate());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void testFileNotFoundException() {
        try {
            Writer testWriter = new Writer(new File("./data/tasks.txt"));
            testWriter.write(task1);
            testWriter.write(task2);
            testWriter.close();
        } catch (FileNotFoundException e) {

        } catch (UnsupportedEncodingException e) {
            fail();
        }
    }

    @Test
    void testUnsupportedEncodingException() {
        try {
            Writer testWriter = new Writer(new File("./data/tasks.txt"));
            testWriter.write(task1);
            testWriter.write(task2);
            testWriter.close();
        } catch (FileNotFoundException e) {
            fail();
        } catch (UnsupportedEncodingException e) {
        }
    }

}

