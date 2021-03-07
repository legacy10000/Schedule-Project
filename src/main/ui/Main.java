package ui;

import java.io.FileNotFoundException;

// Main class to run the Day schedule application
public class Main {
    public static void main(String[] args) {
        try {
            new DayApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run schedule application, file not found.");
        }
    }
}
