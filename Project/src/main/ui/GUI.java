package ui;

import model.Task;
import model.ToDoList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Partially referenced from StackOverFlow
// https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application

public class GUI implements ActionListener {
    ToDoList todolist;
    JFrame frame = new JFrame("Simple ToDoList");
    JFrame toDoListFrame = new JFrame("ToDoList");
    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    JButton button1 = new JButton("Add and View all current Tasks");
    JButton button2 = new JButton("Exit");
    JTextField textField = new JTextField("Enter your task here:");

    // Partially referenced from StackOverFlow
    // https://stackoverflow.com/questions/48298773/opening-a-new-window-on-a-button-click-in-java/48299511
    public GUI(ToDoList todolist) {
        frame.setBounds(500, 500, 700, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        this.todolist = todolist;
        makeTextField();
        firstLayer();
        new Outline().frameWork(frame);
        commandAndListen();
        frame.setVisible(true);
    }

    public void makeTextField() {
        JPanel contentPane = new JPanel();
        frame.add(textField);
        textField.setFont(new Font("Courier", Font.PLAIN, 12));
        textField.setBounds(frame.getInsets().left, frame.getInsets().top, 700, 10);
        contentPane.setBackground(Color.GREEN);
    }

    public void firstLayer() {
        panel1.setLayout(new GridLayout(2, 4, 5, 5));
        panel1.add(button1);
        panel1.add(button2);
        frame.add(panel1, BorderLayout.SOUTH);
    }

    private void commandAndListen() {
        button1.setActionCommand("Add");
        button1.addActionListener(this);
        button2.setActionCommand("Exit");
        button2.addActionListener(this);
    }

    // NOTE: To explicitly view the tasks, delete the existing text in the field and hit add and view all tasks
    @Override
    public void actionPerformed(ActionEvent e) {
        toDoListFrame.setVisible(true);
        if (e.getActionCommand().equals("Add")) {
            PlaySound.completeSound();
            todolist.addTask(new Task(textField.getText(), textField.getText(), textField.getText()));
            System.out.println(textField.getText());
            new ToDoListPanel(todolist);
            frame.dispose();
            System.out.println(textField.getText());
        } else if (e.getActionCommand().equals("Exit")) {
            System.exit(404);
        }
    }
}


