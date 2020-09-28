package ui;

import exceptions.IllegalInputException;
import model.Task;
import model.ToDoList;
import persistence.Reader;
import persistence.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// ToDoList application

public class ToDoListApp {
    private static final String TASKS_FILE = "./data/tasks.txt";
    ToDoList todoList = new ToDoList();
    Scanner input;

    // EFFECTS: runs the ToDoList application
    public ToDoListApp() {
        runToDoList();
    }

    // Referenced used from the TellerApp example provided
    // MODIFIES: this
    // EFFECTS: processes the input given by the user
    private void runToDoList() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        loadTasks();

        while (keepGoing) {
            presentMenu();
            command = input.nextLine();
            command = command.toLowerCase();

            if (command.equals("e")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nHave a nice day!");
    }

    // MODIFIES: this
    // EFFECTS: loads accounts from ACCOUNTS_FILE, if that file exists;
    // otherwise prints an error message
    private void loadTasks() {
        try {
            List<Task> tasks = Reader.readTasks(new File(TASKS_FILE));
            for (Task t1 : tasks) {
                todoList.addTask(t1);
            }
        } catch (IOException e) {
            System.err.println("No file exists");
        }
    }


    // EFFECTS: saves state of lisOfTasks and listOfCompletedTask to ACCOUNTS_FILE
    private void saveTasks() {
        try {
            Writer writer = new Writer(new File(TASKS_FILE));
            for (Task t1: todoList.getListOfTasks()) {
                writer.write(t1);
            }
            writer.close();
            System.out.println("Tasks saved to file " + TASKS_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save tasks to " + TASKS_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    // Referenced used from the TellerApp example provided
    // EFFECTS: presents a set of choices for the user to select from in the form of a menu
    private void presentMenu() {
        System.out.println("\nHi there, kindly select what you would like to do:");
        System.out.println("\ta -> add a task");
        System.out.println("\tr -> remove a task");
        System.out.println("\tc -> mark a task as complete");
        System.out.println("\tm -> modify a task");
        System.out.println("\tv -> view all current tasks as well as tasks saved previously");
        System.out.println("\tct -> view all completed tasks");
        System.out.println("\ts -> save tasks to file");
        System.out.println("\te -> exit");
    }

    // Referenced used from the TellerApp example provided
    // MODIFIES: this
    // EFFECTS: processes the command provided by the user
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddTask();
        } else if (command.equals("r")) {
            doRemoveTask();
        } else if (command.equals("c")) {
            doMarkTaskAsCompleted();
        } else if (command.equals("m")) {
            doModifyTask();
        } else if (command.equals("v")) {
            doViewAllTasks();
        } else if (command.equals("ct")) {
            doViewAllCompletedTasks();
        } else if (command.equals("s")) {
            saveTasks();
        } else {
            System.out.println("Invalid selection, kindly select from the options available.");
        }
    }

    // EFFECTS: displays the tasks that exist; if no tasks are available displays nothing
    private void doViewAllTasks() {
        ArrayList<Task> t1 = todoList.getListOfTasks();
        if (t1.size() > 0) {
            for (Task task : t1) {
                System.out.println(task.getTime() + " " + task.getDescription() + " " + task.getDate());
            }
        } else {
            System.out.println("No tasks available.");
        }
    }

    // EFFECTS: displays the completed tasks that exist; if no tasks are available displays nothing
    private void doViewAllCompletedTasks() {
        ArrayList<Task> t1 = todoList.getListOfCompletedTasks();
        if (t1.size() > 0) {
            for (Task task : t1) {
                System.out.println(task.getTime() + " " + task.getDescription() + " " + task.getDate());
            }
        } else {
            System.out.println("No completed tasks available.");
        }
    }

    // MODIFIES: this
    // EFFECTS: performs adding the given task to the ToDoList
    private void doAddTask() {
        System.out.println(
                "Kindly enter the time, description and date of the task you would like to add on separate lines.");
        String time = input.nextLine();
        String description = input.nextLine();
        String date = input.nextLine();
        todoList.addTask(new Task(time,description,date));
        System.out.println(time + " " + description + " " +  date + " " + "has been added.");
    }

    // MODIFIES: this
    // EFFECTS: performs removing the given task from the ToDoList
    private void doRemoveTask() {
        System.out.println(
                "Select the task you would like to remove by entering the appropriate index number.");
        int index = input.nextInt();
        todoList.removeTask(index);
        System.out.println("The selected task has been successfully removed.");
    }

    // MODIFIES: this
    // EFFECTS: performs marking the given task as completed on the ToDoList
    private void doMarkTaskAsCompleted() {
        System.out.println(
                "Select the task you would like to mark as complete by entering the appropriate index number.");
        int index = input.nextInt();
        todoList.markTaskAsCompleted(index);
        System.out.println("The selected task has been marked as complete. ");
    }

    // MODIFIES: this
    // EFFECTS: performs modifying the given task on the ToDoList
    private void doModifyTask() {
        System.out.println("Select the task you would like to modify by entering the appropriate index number.");
        int index = input.nextInt();
        try {
            todoList.validateIndex(index);
            System.out.println("Enter the modified time and description to be added.");
            String time = input.nextLine();
            String description = input.nextLine();
            System.out.println("Enter the modified date to be added.");
            String date = input.nextLine();
            todoList.modifyTask(index, time, description, date);
            System.out.println("The selected task has been modified.");
        } catch (IllegalInputException e) {
            System.err.println("Invalid index number entered");
        }
    }
}
