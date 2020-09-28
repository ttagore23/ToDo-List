package ui;

import javax.swing.*;
import java.awt.*;

public class Outline {

    JFrame frame;
    JList<String> list;

    public void frameWork(JFrame frame) {
        this.frame = frame;
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        makeScroll();
    }

    public void makeScroll() {
        JTextArea textArea = new JTextArea(20, 80);
        textArea.setFont(new Font("Courier", Font.PLAIN, 12));
        frame.add(textArea);

        JScrollPane scroll = new JScrollPane(list,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        frame.add(scroll);
        scroll.setBounds(frame.getInsets().left, frame.getInsets().top, 700, 300);
    }
}
