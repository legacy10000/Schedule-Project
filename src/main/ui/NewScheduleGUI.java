package ui;

import model.Day;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewScheduleGUI implements ActionListener {

    private Day schedule;

    JFrame newFrame = new JFrame();
    JButton scheduleCreator = new JButton("Generate Schedule");
    JTextArea dayOfWeek = new JTextArea(1, 20);
    JTextArea scheduleName = new JTextArea(1, 20);
    JLabel newLabel = new JLabel("Enter day of week in the 1st box and name of schedule in the 2nd");

    public NewScheduleGUI() {
        scheduleCreator.setActionCommand("generateSchedule");
        scheduleCreator.addActionListener(this);

        newFrame.add(newLabel);
        newFrame.add(dayOfWeek);
        newFrame.add(scheduleName);
        newFrame.add(scheduleCreator);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setSize(1000, 1200);
        newFrame.setLayout(new FlowLayout());
        newFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("generateSchedule")) {
            String dayText = dayOfWeek.getText();
            String nameText = scheduleName.getText();
            schedule = new Day(dayText, nameText);
            newFrame.dispose();
            ScheduleGUI scheduleGUI = new ScheduleGUI(schedule);
        }

    }
}
