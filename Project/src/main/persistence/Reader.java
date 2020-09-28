package persistence;


import model.Task;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A reader that can read the data in a task from a file
public class Reader {
    public static final String DELIMITER = ",";

    // EFFECTS: returns a list of tasks parsed from file; throws
    // IOException if an exception is raised when opening/ reading from the file
    public static List<Task> readTasks(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of tasks parsed from list of strings
    // where each string contains data for one task
    private static List<Task> parseContent(List<String> fileContent) {
        List<Task> tasks = new ArrayList<>();
        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            tasks.add(parseTask(lineComponents));
        }
        return tasks;
    }

    // EFFECTS: returns a list of strings obtained line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 3 where element 0 represents the
    // time of the next task to be constructed, element 1 represents
    // the description, and elements 2 represents the date
    // EFFECTS: returns a task constructed from components
    private static Task parseTask(List<String> components) {
        String time = components.get(0);
        String description = components.get(1);
        String date = components.get(2);
        return new Task(time, description, date);
    }
}
