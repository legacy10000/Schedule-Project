package ui;

import model.Day;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// The UI displayed to the user when they want to make a new schedule.
public class NewScheduleGUI extends ClickSound implements ActionListener {

    private Day schedule;

    JFrame newFrame = new JFrame();
    JButton scheduleCreator = new JButton("Generate Schedule");
    JTextArea dayOfWeek = new JTextArea(1, 20);
    JTextArea scheduleName = new JTextArea(1, 20);
    JLabel newLabel = new JLabel("Enter day of week in the 1st box and name of schedule in the 2nd");

    // MODIFIES: this
    // EFFECTS: initializes the NewSchedule GUI menu and displays it
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

    // MODIFIES: this
    // EFFECTS: plays click sound, creates an new schedule according to user desires and
    //          removes the Opening GUI window and transitions Schedule GUI
    @Override
    public void actionPerformed(ActionEvent e) {
        playClick();
        if (e.getActionCommand().equals("generateSchedule")) {
            String dayText = dayOfWeek.getText();
            String nameText = scheduleName.getText();
            schedule = new Day(dayText, nameText);
            newFrame.dispose();
            ScheduleGUI scheduleGUI = new ScheduleGUI(schedule);
        }

    }
}
