package model;

import java.util.LinkedList;

import exceptions.BeginStopRangeException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Represents a day with 24 hours to schedule
public class Day implements Writable {
    private LinkedList<Activity> plan;  // the 24 hour plan for the day
    private String planName;            // the name of the plan being created
    private String dayOfWeek;           // day of the week

    //EFFECTS: Creates a new day to insert activities in starting with "Available" slots from 0:00-1:00 to 23:00-24:00,
    //         also includes day of the week and name of schedule
    public Day(String dw, String pn) throws BeginStopRangeException {
        planName = pn;
        dayOfWeek = dw;
        plan = new LinkedList<>();
        for (int i = 0; i <= 23; i++) {
            plan.addLast(new Activity("Available", i, (i + 1)));
        }

    }

    //MODIFIES: this
    //EFFECTS: adds a new activity in the schedule in start time order
    public void addActivity(Activity a) throws BeginStopRangeException {
        for (int i = a.getStart(); i < a.getEnd(); i++) {
            plan.set(i, new Activity(a.getActName(), i, (i + 1)));
        }
    }

    //MODIFIES: this
    //EFFECTS: removes the specified activity in the list if found (including same names at diff times)
    public void removeActivity(String s) throws BeginStopRangeException {
        for (int i = 0; i < plan.size(); i++) {
            if (plan.get(i).getActName().equals(s)) {
                plan.set(i, new Activity("Available", i, (i + 1)));
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: clears the current schedule entirely, resetting every slot to available
    public void clearSchedule() throws BeginStopRangeException {
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("plan name", planName);
        json.put("day of the week", dayOfWeek);
        json.put("plan", planToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray planToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Activity a : plan) {
            jsonArray.put(a.toJson());
        }

        return jsonArray;
    }
}
