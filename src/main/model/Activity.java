package model;


// Represents an activity scheduled in a day, its name, start time and end time
public class Activity {
    private String actName;  // the name of the activity
    private int start;       // the starting time of the activity
    private int end;         // the ending time of the activity

    //REQUIRES: begin from 0 to 23, stop from 1 to 24, begin < stop
    //EFFECTS: creates a new activity to be input in the schedule, with name, start and end time
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
