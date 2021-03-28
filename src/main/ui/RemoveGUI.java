package ui;

import model.Day;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// The UI displayed to users when they want to remove an activity
public class RemoveGUI extends ClickSound implements ActionListener {

    private Day schedule;
    JFrame removeFrame = new JFrame();
    JLabel actLabel = new JLabel("Enter name of activity to be removed");
    JTextArea name = new JTextArea(1, 20);
    JButton activityRemover = new JButton("Remove Activity");

    // MODIFIES: this
    // EFFECTS: initializes the Remove GUI menu and displays it
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


    // MODIFIES: this
    // EFFECTS: plays click sound, removes an activity from the day schedule and removes
    //          the Remove GUI window and transitions Schedule GUI
    @Override
    public void actionPerformed(ActionEvent e) {
        playClick();
        if (e.getActionCommand().equals("removeActivity")) {
            String actName = name.getText();
            schedule.removeActivity(actName);
            removeFrame.dispose();
            ScheduleGUI scheduleGUI = new ScheduleGUI(schedule);
        }

    }
}
