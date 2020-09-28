package model;

// Represents a task which has a time, description and a date.

import persistence.Reader;
import persistence.Saveable;

import java.io.PrintWriter;

// Represents a task which has a time, description and date

public class Task implements Saveable {

    private String time;
    private String description;
    private String date;

    // REQUIRES: time, description and date have at least one character
    // EFFECTS: constructs a task with a time, description and date;
    // nextTaskIndex is the index of the next task to be constructed
    // NOTE: this constructor is to used only when constructing a task from data stored in file
    public Task(String time, String description, String date) {

        this.time = time;
        this.description = description;
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(time);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(description);
        printWriter.print(Reader.DELIMITER);
        printWriter.println(date);
    }
}


