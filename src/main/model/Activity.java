package model;


// Represents an activity scheduled in a day, its name, (start time and end time)?
public class Activity {
    private String actName;  // the name of the activity
    private int start;       // the starting time of the activity
    private int end;         // the ending time of the activity

    //REQUIRES: begin and stop range from 0 to 24 inclusive, begin < stop
    //EFFECTS: creates a new activity to be input in the schedule, taking up hourly slots
    public Activity(String name, int begin, int stop) {
        actName = name;
        start = begin;
        end = stop;

    }

    //getter
    public int getStart() {
        return start;
    }

    //getter
    public int getEnd() {
        return end;
    }

    //getter
    public String getActName() {
        return actName;
    }
}
