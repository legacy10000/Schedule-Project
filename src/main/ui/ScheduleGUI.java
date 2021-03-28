package ui;

import model.Activity;
import model.Day;
import persistence.JsonDayWriter;
import persistence.JsonDayReader;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;


// The main menu UI for users when dealing with their schedule
// Citation: some code was obtained from JsonSerializationDemo and Oracles ListDemo.Java
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// URL: https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
public class ScheduleGUI extends ClickSound implements ActionListener {
    private JList dayList;
    private DefaultListModel dayListModel;

    private static final String addString = "Add";
    private static final String removeString = "Remove";
    private static final String clearString = "Clear";
    private static final String saveString = "Save";
    private static final String loadString = "Load";
    private JsonDayWriter jsonDayWriter;
    private JsonDayReader jsonDayReader;
    private static final String JSON_STORE_DAY = "./data/day.json";

    private Day schedule;

    JFrame scheduleFrame = new JFrame();
    JButton addButton = new JButton(addString);
    JButton removeButton = new JButton(removeString);
    JButton clearButton = new JButton(clearString);
    JButton saveButton = new JButton(saveString);
    JButton loadButton = new JButton(loadString);

    // MODIFIES: this
    // EFFECTS: initializes the Schedule GUI menu and displays it
    public ScheduleGUI(Day d) {
        jsonDayWriter = new JsonDayWriter(JSON_STORE_DAY);
        jsonDayReader = new JsonDayReader(JSON_STORE_DAY);
        schedule = d;
        JLabel namesLabel = new JLabel("Day: " + d.getDayOfWeek() + " Name: " + d.getPlanName());
        dayListModel = new DefaultListModel();
        arrayToListModel(d, dayListModel);
        dayList = new JList(dayListModel);
        dayList.setVisibleRowCount(24);
        JScrollPane dayListScrollPane = new JScrollPane(dayList);
        initializeButtons();

        scheduleFrame.add(dayListScrollPane, BorderLayout.CENTER);
        scheduleFrame.add(namesLabel);
        addButtons();
        scheduleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        scheduleFrame.setSize(1000, 1200);
        scheduleFrame.setLayout(new FlowLayout());
        scheduleFrame.setVisible(true);
    }


    // MODIFIES: DefaultListModel
    // EFFECTS: adds the activities from a day to the JList
    private void arrayToListModel(Day d, DefaultListModel dlm) {
        dlm.clear();
        for (Activity a : d.getPlan()) {
            dlm.add(a.getStart(), "Name: " + a.getActName() + " " + a.getStart() + ":00-" + a.getEnd() + ":00");
        }

    }


    // MODIFIES: this
    // EFFECTS: initializes events to the action listeners
    private void initializeButtons() {
        addButton.setActionCommand("addSchedule");
        addButton.addActionListener(this);
        removeButton.setActionCommand("removeSchedule");
        removeButton.addActionListener(this);
        clearButton.setActionCommand("clearSchedule");
        clearButton.addActionListener(this);
        saveButton.setActionCommand("saveSchedule");
        saveButton.addActionListener(this);
        loadButton.setActionCommand("loadSchedule");
        loadButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: adds all the main buttons
    private void addButtons() {
        scheduleFrame.add(addButton);
        scheduleFrame.add(removeButton);
        scheduleFrame.add(clearButton);
        scheduleFrame.add(saveButton);
        scheduleFrame.add(loadButton);
    }

    // MODIFIES: this
    // EFFECTS: plays a button click and performs one of the 5 menu actions, add,
    //          remove, clear, save or load depending on which button was clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        playClick();
        if (e.getActionCommand().equals("addSchedule")) {
            scheduleFrame.dispose();
            AddGUI addGUI = new AddGUI(schedule);
        }
        if (e.getActionCommand().equals("removeSchedule")) {
            scheduleFrame.dispose();
            RemoveGUI removeGUI = new RemoveGUI(schedule);
        }
        if (e.getActionCommand().equals("clearSchedule")) {
            schedule.clearSchedule();
            scheduleReset();
        }
        if (e.getActionCommand().equals("saveSchedule")) {
            JLabel saveStatus = new JLabel("Saved " + schedule.getPlanName() + " to " + JSON_STORE_DAY);
            saveSchedule();
            scheduleFrame.add(saveStatus);
            scheduleFrame.validate();
        }
        if (e.getActionCommand().equals("loadSchedule")) {
            loadSchedule();
            scheduleReset();
        }
    }

    // MODIFIES: this
    // EFFECTS: updates Schedule GUI to reflect new changes
    private void scheduleReset() {
        scheduleFrame.dispose();
        ScheduleGUI scheduleGUI = new ScheduleGUI(schedule);
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

    // MODIFIES: this
    // EFFECTS: saves schedule to file
    private void saveSchedule() {
        try {
            jsonDayWriter.openFile();
            jsonDayWriter.write(schedule);
            jsonDayWriter.closeFile();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_DAY);
        }
    }

}
