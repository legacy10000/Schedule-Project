package persistence;

import model.Activity;
import model.Day;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads day from JSON data stored in file
// Citation: code was obtained from JsonSerializationDemo
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonDayReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonDayReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads day from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Day read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDay(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses day from JSON object and returns it
    private Day parseDay(JSONObject jsonObject) {
        String dw = jsonObject.getString("day of the week");
        String pn = jsonObject.getString("plan name");
        Day day = new Day(dw, pn);
        addDay(day, jsonObject);
        return day;
    }

    // MODIFIES: day
    // EFFECTS: parses activities(plan) from JSON object and adds them to day
    private void addDay(Day day, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("plan");
        for (Object json : jsonArray) {
            JSONObject nextActivity = (JSONObject) json;
            addActive(day, nextActivity);
        }
    }

    // MODIFIES: day
    // EFFECTS: parses activity from JSON object and adds it to day
    private void addActive(Day day, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int start = jsonObject.getInt("start");
        int end = jsonObject.getInt("end");
        Activity a = new Activity(name, start, end);
        day.addActivity(a);
    }

}
