package persistence;

import org.json.JSONObject;

// interface method to return object as JSON
// Citation: code was obtained from JsonSerializationDemo
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public interface Writable {
    // EFFECTS: returns this as JSON object for storage
    JSONObject toJson();

}
