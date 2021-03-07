package persistence;

import java.io.PrintWriter;

import model.Day;
import org.json.JSONObject;

import java.io.*;

// Represents a writer that writes JSON representation of day to file
// Citation: code was obtained from JsonSerializationDemo
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonDayWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to the destination file
    public JsonDayWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer, throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void openFile() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of day to file
    public void write(Day dy) {
        JSONObject json = dy.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void closeFile() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file, meaning it is being saved
    private void saveToFile(String json) {
        writer.print(json);
    }
}
