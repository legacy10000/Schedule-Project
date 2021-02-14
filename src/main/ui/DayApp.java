package ui;

import model.Activity;
import model.Day;

import java.util.Scanner;

// The ui application for the Day schedule
public class DayApp {
    private Day schedule;
    private Scanner input;

    // EFFECTS: runs the schedule app
    public DayApp() {
        runDayApp();
    }

    //MODIFIES: this
    //EFFECTS: processes users inputs in the application
    private void runDayApp() {
        boolean stillActive = true;
        String command;

        System.out.println("Welcome to the 24-Hour Schedule application! Please enter the following information.");
        input = new Scanner(System.in);
        initializeSchedule();

        while (stillActive) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
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
    // EFFECTS: initializes a day for the user with the day of the week and plan name
    private void initializeSchedule() {
        System.out.print("Enter day of week: ");
        String week = input.nextLine();
        System.out.print("Enter schedule name: ");
        String name = input.nextLine();
        schedule = new Day(week, name);

    }

    // EFFECTS: displays the menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta --> Add activity");
        System.out.println("\tr --> Remove activity");
        System.out.println("\tc --> Clear schedule");
        System.out.println("\td --> Display schedule");
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
}