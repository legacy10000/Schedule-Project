package ui;

import model.Day;
import persistence.JsonDayReader;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

// The Opening UI is the first ui displayed to the user, with load and new options
// Citation: some code was obtained from JsonSerializationDemo
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class OpeningGUI extends ClickSound implements ActionListener {

    private static final String newString = "New";
    private static final String loadString = "Load";
    private static final String JSON_STORE_DAY = "./data/day.json";

    private Day schedule;
    private JsonDayReader jsonDayReader;

    JFrame openingFrame = new JFrame();
    JButton newButton = new JButton(newString);
    JButton loadButton = new JButton(loadString);


    // MODIFIES: this
    // EFFECTS: initializes the Opening GUI menu and displays it
    public OpeningGUI() {
        jsonDayReader = new JsonDayReader(JSON_STORE_DAY);
        JLabel welcomeLabel = new JLabel("Welcome to the 24-Hour Schedule application! Please choose beside!");
        openingFrame.add(welcomeLabel);

        newButton.setActionCommand("newSchedule");
        newButton.addActionListener(this);
        loadButton.setActionCommand("loadSchedule");
        loadButton.addActionListener(this);
        openingFrame.add(newButton);
        openingFrame.add(loadButton);

        openingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        openingFrame.setSize(1000, 1200);
        openingFrame.setLayout(new FlowLayout());
        openingFrame.setVisible(true);

    }


    // MODIFIES: this
    // EFFECTS: plays click sound, removes the Opening GUI window and transitions to NewSchedule GUI
    //          or Schedule GUI both changing schedule, depending on which button was clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        playClick();
        if (e.getActionCommand().equals("newSchedule")) {
            openingFrame.dispose();
            NewScheduleGUI newScheduleGUI = new NewScheduleGUI();
        }
        if (e.getActionCommand().equals("loadSchedule")) {
            loadSchedule();
            openingFrame.dispose();
            ScheduleGUI scheduleGUI = new ScheduleGUI(schedule);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads schedule from file
    private void loadSchedule() {
        try {
            schedule = jsonDayReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_DAY);
        }
    }

}