package ui;

import model.Task;
import model.ToDoList;
import persistence.Reader;
import persistence.Writer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

// Partially referenced from StackOverFlow
// https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
public class ToDoListPanel extends JFrame implements ActionListener {
    ToDoList todoList;
    JFrame frame = new JFrame("Simple ToDoList");
    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    JButton button3 = new JButton("Remove Task"); // Put this in the view all tasks option
    JButton button4 = new JButton("Back");
    JButton button5 = new JButton("Save");
    JButton button6 = new JButton("Load saved tasks and view current tasks");

    JList<String> list = new JList<>();
    private static final String TASKS_FILE = "./data/tasks.txt";

    // Partially referenced from StackOverFlow
    // https://stackoverflow.com/questions/48298773/opening-a-new-window-on-a-button-click-in-java/48299511
    public ToDoListPanel(ToDoList todoList) {
        frame.setBounds(500, 500, 700, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        this.todoList = todoList;
        firstLayer();
        outline();
        view();
        commandAndListen();
        makeScroll();
        list.setFixedCellHeight(20);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        frame.setVisible(true);
    }

    private void outline() {
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void makeScroll() {
        JTextArea textArea = new JTextArea(20, 80);
        textArea.setFont(new Font("Courier", Font.PLAIN, 12));
        frame.add(textArea);

        JScrollPane scroll = new JScrollPane(list,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setViewportView(list);
        frame.add(scroll);
        scroll.setBounds(frame.getInsets().left, frame.getInsets().top, 700, 300);
    }

    public void firstLayer() {
        panel1.setLayout(new GridLayout(2, 4, 5, 5));
        panel1.add(button3);
        panel1.add(button4);
        panel1.add(button5);
        panel1.add(button6);
        frame.add(panel1, BorderLayout.SOUTH);
    }

    private void commandAndListen() {
        button3.setActionCommand("Remove");
        button3.addActionListener(this);
        button4.setActionCommand("Back");
        button4.addActionListener(this);
        button5.setActionCommand("Save");
        button5.addActionListener(this);
        button6.setActionCommand("Load");
        button6.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Remove":
                remove();
                break;
            case "Back":
                frame.dispose();
                new GUI(todoList);
                break;
            case "Save":
                save();
                break;
            case "Load":
                load();
                break;
        }
    }

    private void remove() {
        int t1 = list.getSelectedIndex();
        System.out.println(t1);
        System.out.println(todoList.getListOfTasks().get(t1));
        todoList.removeTask(t1);
    }

    private void view() {
        String[] listData = new String[todoList.getListOfTasks().size()];
        for (int i = 0; i < listData.length; i++) {
            Task task = todoList.getListOfTasks().get(i);
            listData[i] = task.getDescription();
            System.out.println(task.getDescription());
        }
        list.setListData(listData);
    }

    private void load() {
        try {
            List<Task> tasks = Reader.readTasks(new File(TASKS_FILE));
            for (Task t1 : tasks) {
                todoList.addTask(t1);
            }
            System.out.println(tasks);
        } catch (IOException e) {
            System.err.println("No file exists");
        }
        view();
    }

    private void save() {
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
}
