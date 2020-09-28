package persistence;

import model.Task;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {
    Reader reader = new Reader();

    @Test
    void testParseAccountsFile1() {
        try {
            List<Task> tasks = reader.readTasks(new File("./data/tasks.txt"));
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
    void testIOException() {
        try {
            Reader.readTasks(new File("./path/does/not/exist/testTask.txt"));
        } catch (IOException e) {
            // expected
        }
    }
}
