package model;

import java.util.LinkedList;

// Represents a day with 24 hours to schedule, day of the week, day number, month and year
public class Day {
    private LinkedList<Activity> plan;  // the 24 hour plan for the day
    private String planName;            // the name of the plan being created
    private String dayOfWeek;           // day of the week
    private int dayNumber;              // day number
    private String month;               // the current month
    private int year;                   // the current year

    public Day(String dw, String pn, int dn, String m, int y) {
        plan = new LinkedList<Activity>();
        planName = pn;
        dayOfWeek = dw;
        dayNumber = dn;
        month = m;
        year = y;

    }

    //add activity
    //MODIFIES: this
    //EFFECTS: adds a new activity in the list in start time order
    public void addActivity(Activity a) {
        if (plan.size() == 0) {
            plan.add(a.getStart(), a);
        } else if (plan.getFirst().getStart() > a.getStart()) {
            plan.add(0, a);
        } else if (plan.get((plan.size() - 1)).getStart() < a.getStart()) {
            plan.add(plan.size(), a);
        } else {
            int pos = 0;
            while (plan.get(pos).getStart() < a.getStart()) {
                pos++;
            }
            plan.add(pos, a);
        }

    }

    //remove activity
    //MODIFIES: this
    //EFFECTS: removes the specified activity in the list if found, if share the same name first is removed
    public void removeActivity(Activity a) {
        plan.removeFirstOccurrence(a);
    }

    //remove activity with name plan.get(i).getActName() == s
    //MODIFIES: this
    //EFFECTS: removes the specified activity in the list if found (including same names at diff times)
    public void removeActivityName(String s) {
        for (int i = 0; i <= plan.size(); i++) {
            if (plan.get(i).getActName() == s) {
                plan.remove(i);
            }
        }
    }

    //clear schedule
    //MODIFIES: this
    //EFFECTS: clears the current schedule entirely
    public void clearSchedule() {
        plan.clear();
    }

    //get plan
    public LinkedList<Activity> getPlan() {
        return plan;
    }

    //get day of week
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    //get dayNum
    public int getDayNumber() {
        return dayNumber;
    }

    //get month
    public String getMonth() {
        return month;
    }


    //get year
    public int getYear() {
        return year;
    }


    //set plan

    //set dayNum

    //set month

    //set year
}
