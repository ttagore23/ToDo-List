package model;

// Represents a ToDoList to which tasks can be added, removed, modified and marked complete. The user can also view all
// the tasks as well as all the completed tasks.

import exceptions.IllegalInputException;
import java.util.ArrayList;

public class ToDoList {
    ArrayList<Task> listOfTasks;
    ArrayList<Task> listOfCompletedTasks;

    public ArrayList<Task> getListOfTasks() {
        return listOfTasks;
    }

    public ArrayList<Task> getListOfCompletedTasks() {
        return listOfCompletedTasks;
    }

    public ToDoList() {
        listOfTasks = new ArrayList<>();
        listOfCompletedTasks = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add the given task to the ToDoList
    public void addTask(Task t1) {
        listOfTasks.add(t1);
    }

    // REQUIRES: the ToDoList have at least one task and the index >= 0
    // MODIFIES: this
    // EFFECTS: remove the given task from the ToDoList
    public void removeTask(int index) {
        Task t1 = listOfTasks.get(index);
        listOfTasks.remove(t1);
    }

    // REQUIRES: the ToDoList have at least one task and the index >= 0
    // MODIFIES: this
    // EFFECTS: remove the given task from listOfTasks and add it to listOfCompletedTasks
    public void markTaskAsCompleted(int index) {
        Task t1 = listOfTasks.get(index);
        listOfTasks.remove(t1);
        listOfCompletedTasks.add(t1);
    }

    // REQUIRES: the ToDoList have at least one task and the index >= 0
    // MODIFIES: this, Task
    // EFFECTS: enables the user to modify the time, description and date of a task
    public void modifyTask(int index, String time, String description, String date) {
        Task t1 = listOfTasks.get(index);
        t1.setTime(time);
        t1.setDescription(description);
        t1.setDate(date);
    }

    // MODIFIES: this
    // EFFECTS: throws an exception if the index < 0
    public void validateIndex(int index) throws IllegalInputException {
        if (index < 0) {
            throw new IllegalInputException();
        }
    }

    // MODIFIES: nothing
    // EFFECTS: return true if the listOfTasks contains the provided task
    public boolean contains(Task task) {
        return listOfTasks.contains(task);
    }

    // MODIFIES: nothing
    // EFFECTS: return true if the listOfTasks contains the provided task
    public int size() {
        return listOfTasks.size();
    }

}
