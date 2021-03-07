package ui;

import model.Activity;
import model.Day;
import persistence.JsonDayReader;
import persistence.JsonDayWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// The ui application for the Day schedule
// Citation: some of the code was obtained from JsonSerializationDemo
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class DayApp {
    private static final String JSON_STORE_DAY = "./data/day.json";
    private Day schedule;
    private Scanner input;
    private JsonDayWriter jsonDayWriter;
    private JsonDayReader jsonDayReader;

    // EFFECTS: runs the schedule app
    public DayApp() throws FileNotFoundException {
        runDayApp();
    }

    //MODIFIES: this
    //EFFECTS: processes users inputs in the application
    private void runDayApp() {
        boolean stillActive = true;
        String command;

        System.out.println("Welcome to the 24-Hour Schedule application! Please answer the following information.");
        input = new Scanner(System.in);
        jsonDayWriter = new JsonDayWriter(JSON_STORE_DAY);
        jsonDayReader = new JsonDayReader(JSON_STORE_DAY);
        initializeSchedule();

        while (stillActive) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                saveBeforeQuit();
                stillActive = false;
            } else {
                doCommand(command);
            }
        }

        System.out.println("\nHere's your schedule one last time before you go!");
        printDaySchedule();
        System.out.println("\nSee you next time!");

    }

    //MODIFIES: this
    // EFFECTS: starts a new schedule for the user or or loads previous schedule
    private void initializeSchedule() {
        System.out.print("Do you want to load previous schedule? Respond with 'y' else new enter new details: ");
        String answer = input.next();
        answer = answer.toLowerCase();
        if (answer.equals("y")) {
            loadSchedule();
        } else {
            newSchedule();
        }

    }

    // EFFECTS: displays the menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta --> Add activity");
        System.out.println("\tr --> Remove activity");
        System.out.println("\tc --> Clear schedule");
        System.out.println("\td --> Display schedule");
        System.out.println("\ts --> Save current schedule to file");
        System.out.println("\tl --> Load last saved schedule from file");
        System.out.println("\tq --> Quit");
    }

    //MODIFIES: this
    //EFFECTS: does the users requested command
    private void doCommand(String command) {
        switch (command) {
            case "a":
                doAdd();
                break;
            case "r":
                doRemove();
                break;
            case "c":
                doClear();
                break;
            case "d":
                printDaySchedule();
                break;
            case "s":
                saveSchedule();
                break;
            case "l":
                loadSchedule();
                break;
            default:
                System.out.println("Please enter a valid input.");
                break;
        }
    }

    //MODIFIES: this
    //EFFECTS: adds an activity to a day schedule
    private void doAdd() {
        System.out.print("Enter activity name: ");
        String name = input.next();
        System.out.print("Enter start time: ");
        int st = input.nextInt();
        System.out.print("Enter end time: ");
        int ed = input.nextInt();
        if (st == ed) {
            System.out.println("Start time and end time must be different, please enter again.");
        } else if (st >= ed) {
            System.out.println("Start time must be less than end time, please enter again.");
        } else if ((st < 0) || (st > 23)) {
            System.out.println("Start time ranges from 0 to 23 integers inclusive, please enter again.");
        } else if (ed > 24) {
            System.out.println("End time ranges from 1 to 24 integers inclusive, please enter again.");
        } else {
            Activity activity = new Activity(name, st, ed);
            schedule.addActivity(activity);
        }
    }

    //MODIFIES: this
    //EFFECTS: removes an activity from the entire schedule by name
    private void doRemove() {
        System.out.print("Enter activity name to be removed: ");
        String name = input.next();
        schedule.removeActivity(name);
    }

    //MODIFIES: this
    //EFFECTS: clears the entire day schedule to a blank state, making all slots "Available"
    private void doClear() {
        System.out.print("Are you sure you want to clear the schedule? Confirm with 'y', else returning to menu: ");
        String answer = input.next();
        answer = answer.toLowerCase();
        if (answer.equals("y")) {
            schedule.clearSchedule();
        }
    }

    //EFFECTS: prints out the schedule for the entire day
    private void printDaySchedule() {
        for (int i = 0; i < schedule.getPlan().size(); i++) {
            Activity h = schedule.getPlan().get(i);
            if (i == 0) {
                System.out.println("Title: " + schedule.getPlanName());
                System.out.println("Day: " + schedule.getDayOfWeek());
            }
            System.out.print(h.getStart() + ":00 to " + h.getEnd() + ":00 - ");
            System.out.println(h.getActName());
        }
    }

    // EFFECTS: saves the schedule to file
    private void saveSchedule() {
        try {
            jsonDayWriter.openFile();
            jsonDayWriter.write(schedule);
            jsonDayWriter.closeFile();
            System.out.println("Saved " + schedule.getPlanName() + " to " + JSON_STORE_DAY);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_DAY);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads schedule from file
    private void loadSchedule() {
        try {
            schedule = jsonDayReader.read();
            System.out.println("Loaded " + schedule.getPlanName() + " from " + JSON_STORE_DAY);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_DAY);
        }
    }

    // EFFECTS: saves the schedule to file before quitting if user replies with 'y'
    private void saveBeforeQuit() {
        System.out.print("Do you want to save before quitting?? Confirm with 'y', else no final save. ");
        String answer = input.next();
        answer = answer.toLowerCase();
        if (answer.equals("y")) {
            saveSchedule();
        }
    }

    //MODIFIES: this
    // EFFECTS: initializes a new day for the user with the day of the week and plan name
    public void newSchedule() {
        System.out.print("Enter schedule name: ");
        String name = input.next();
        System.out.print("Enter day of week: ");
        String week = input.next();
        schedule = new Day(week, name);
    }

}