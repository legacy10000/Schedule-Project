package model;

import java.util.LinkedList;

// Represents a day with 24 hours to schedule
public class Day {
    private LinkedList<Activity> plan;  // the 24 hour plan for the day
    private String planName;            // the name of the plan being created
    private String dayOfWeek;           // day of the week

    public Day(String dw, String pn) {
        planName = pn;
        dayOfWeek = dw;
        plan = new LinkedList<>();
        for (int i = 0; i <= 23; i++) {
            plan.addLast(new Activity("Available", i, (i + 1)));
        }

    }

    //add activity
    //MODIFIES: this
    //EFFECTS: adds a new activity in the schedule in start time order
    public void addActivity(Activity a) {
        for (int i = a.getStart(); i < a.getEnd(); i++) {
            plan.set(i, new Activity(a.getActName(), i, (i + 1)));
        }
    }

    //remove activity with name plan.get(i).getActName() == s
    //MODIFIES: this
    //EFFECTS: removes the specified activity in the list if found (including same names at diff times)
    public void removeActivity(String s) {
        for (int i = 0; i < plan.size(); i++) {
            if (plan.get(i).getActName().equals(s)) {
                plan.set(i, new Activity("Available", i, (i + 1)));
            }
        }
    }

    //clear schedule
    //MODIFIES: this
    //EFFECTS: clears the current schedule entirely, resetting every slot to available
    public void clearSchedule() {
        for (int i = 0; i < plan.size(); i++) {
            plan.set(i, new Activity("Available", i, (i + 1)));
        }
    }

    //getter
    public String getPlanName() {
        return planName;
    }

    //getter
    public LinkedList<Activity> getPlan() {
        return plan;
    }

    //getter
    public String getDayOfWeek() {
        return dayOfWeek;
    }

}
