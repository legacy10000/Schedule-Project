package ui;

import model.Activity;
import model.Day;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddGUI implements ActionListener {

    private Day schedule;
    JFrame addFrame = new JFrame();
    JLabel actLabel = new JLabel("Enter name, start time and then end time");
    JTextArea name = new JTextArea(1, 20);
    JTextArea startTime = new JTextArea(1, 4);
    JTextArea endTime = new JTextArea(1, 4);
    JButton activityAdder = new JButton("Add Activity");

    public AddGUI(Day d) {
        schedule = d;
        activityAdder.setActionCommand("addActivity");
        activityAdder.addActionListener(this);

        addFrame.add(actLabel);
        addFrame.add(name);
        addFrame.add(startTime);
        addFrame.add(endTime);
        addFrame.add(activityAdder);
        addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addFrame.setSize(1000, 1200);
        addFrame.setLayout(new FlowLayout());
        addFrame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("addActivity")) {
            String actName = name.getText();
            int start = Integer.parseInt(startTime.getText());
            int end = Integer.parseInt(endTime.getText());
            Activity activity = new Activity(actName, start, end);
            schedule.addActivity(activity);
            addFrame.dispose();
            ScheduleGUI scheduleGUI = new ScheduleGUI(schedule);
        }

    }
}