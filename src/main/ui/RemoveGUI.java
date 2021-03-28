package ui;

import model.Day;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveGUI implements ActionListener {

    private Day schedule;
    JFrame removeFrame = new JFrame();
    JLabel actLabel = new JLabel("Enter name of activity to be removed");
    JTextArea name = new JTextArea(1, 20);
    JButton activityRemover = new JButton("Remove Activity");

    public RemoveGUI(Day d) {
        schedule = d;
        activityRemover.setActionCommand("removeActivity");
        activityRemover.addActionListener(this);

        removeFrame.add(actLabel);
        removeFrame.add(name);
        removeFrame.add(activityRemover);
        removeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        removeFrame.setSize(1000, 1200);
        removeFrame.setLayout(new FlowLayout());
        removeFrame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("removeActivity")) {
            String actName = name.getText();
            schedule.removeActivity(actName);
            removeFrame.dispose();
            ScheduleGUI scheduleGUI = new ScheduleGUI(schedule);
        }

    }
}
