package ui;

import model.Day;
import persistence.JsonDayReader;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

public class OpeningGUI implements ActionListener {

    private static final String newString = "New";
    private static final String loadString = "Load";
    private static final String JSON_STORE_DAY = "./data/day.json";

    private JLabel welcomeLabel;

    private Day schedule;
    private JsonDayReader jsonDayReader;

    JFrame openingFrame = new JFrame();
    JButton newButton = new JButton(newString);
    JButton loadButton = new JButton(loadString);

    public OpeningGUI() {
        jsonDayReader = new JsonDayReader(JSON_STORE_DAY);
        welcomeLabel = new JLabel("Welcome to the 24-Hour Schedule application! Please choose beside!");
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


    //takes to one of the two menus
    @Override
    public void actionPerformed(ActionEvent e) {
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