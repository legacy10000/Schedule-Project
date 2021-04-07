package model;


import exceptions.BeginStopRangeException;
import persistence.Writable;
import org.json.JSONObject;

// Represents an activity scheduled in a day, its name, start time and end time
public class Activity implements Writable {
    private String actName;  // the name of the activity
    private int start;       // the starting time of the activity
    private int end;         // the ending time of the activity

    //EFFECTS: creates a new activity to be input in the schedule, with name, start and end time
    //         throws BeginStopRangeException if begin and stop values are outside range or begin > stop
    public Activity(String name, int begin, int stop) throws BeginStopRangeException {
        if (begin > stop || begin < 0 || begin > 23 || stop < 1 || stop > 24) {
            throw new BeginStopRangeException();
        } else {
            actName = name;
            start = begin;
            end = stop;
        }

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

    // DESCRIPTION?
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", actName);
        json.put("start", start);
        json.put("end", end);
        return json;
    }
}
