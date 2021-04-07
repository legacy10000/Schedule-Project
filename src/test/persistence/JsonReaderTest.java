package persistence;

import exceptions.BeginStopRangeException;
import model.Day;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// test class for JsonReaderTest
// Citation: code was obtained from JsonSerializationDemo
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonDayReader reader = new JsonDayReader("./data/noSuchFile.json");
        try {
            Day d = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptySchedule() {
        JsonDayReader reader = new JsonDayReader("./data/testReaderEmptySchedule.json");
        try {
            Day d = reader.read();
            assertEquals("Test Plan", d.getPlanName());
            assertEquals("Sunday", d.getDayOfWeek());
            assertEquals("Available", d.getPlan().get(0).getActName());
            assertEquals("Available", d.getPlan().get(12).getActName());
            assertEquals("Available", d.getPlan().get(23).getActName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderEmptyScheduleWithStuff() {
        JsonDayReader reader = new JsonDayReader("./data/testReaderEmptyScheduleWithStuff.json");
        try {
            Day d = reader.read();
            assertEquals("Test Plan With Stuff", d.getPlanName());
            assertEquals("Wednesday", d.getDayOfWeek());
            assertEquals("Sleep Morning", d.getPlan().get(0).getActName());
            assertEquals("Sleep Morning", d.getPlan().get(4).getActName());
            assertEquals("Workout", d.getPlan().get(9).getActName());
            assertEquals("Available", d.getPlan().get(10).getActName());
            assertEquals("Dinner", d.getPlan().get(17).getActName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
