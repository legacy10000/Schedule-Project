package persistence;

import model.Day;
import model.Activity;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// test class for JsonWriterTest
// Citation: code was obtained from JsonSerializationDemo
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Day d = new Day("Monday", "Blues");
            JsonDayWriter writer = new JsonDayWriter("./data/my\0illegal:fileName.json");
            writer.openFile();
            fail("The IOException was expected");
        } catch (IOException e) {
            // Yay good job!!!
        }
    }

    @Test
    void testWriterEmptySchedule() {
        try {
            Day d = new Day("Tuesday", "Sigh...");
            JsonDayWriter writer = new JsonDayWriter("./data/testWriterEmptySchedule.json");
            writer.openFile();
            writer.write(d);
            writer.closeFile();

            JsonDayReader reader = new JsonDayReader("./data/testWriterEmptySchedule.json");
            d = reader.read();
            assertEquals("Tuesday", d.getDayOfWeek());
            assertEquals("Sigh...", d.getPlanName());
            assertEquals("Available", d.getPlan().get(0).getActName());
            assertEquals("Available", d.getPlan().get(12).getActName());
            assertEquals("Available", d.getPlan().get(23).getActName());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterScheduleWithStuff() {
        try {
            Day d = new Day("Friday", "Last day!");
            Activity sleep = new Activity("Sleep Morning", 0, 9);
            Activity workout = new Activity("Workout", 9, 10);
            Activity dinner = new Activity("Dinner", 17, 18);
            d.addActivity(sleep);
            d.addActivity(workout);
            d.addActivity(dinner);
            JsonDayWriter writer = new JsonDayWriter("./data/testWriterScheduleWithStuff.json");
            writer.openFile();
            writer.write(d);
            writer.closeFile();

            JsonDayReader reader = new JsonDayReader("./data/testWriterScheduleWithStuff.json");
            d = reader.read();
            assertEquals("Friday", d.getDayOfWeek());
            assertEquals("Last day!", d.getPlanName());
            assertEquals(sleep.getActName(), d.getPlan().get(sleep.getStart()).getActName());
            assertEquals(sleep.getActName(), d.getPlan().get((sleep.getStart() + 4)).getActName());
            assertEquals(workout.getActName(), d.getPlan().get(workout.getStart()).getActName());
            assertEquals("Available", d.getPlan().get(workout.getStart() + 1).getActName());
            assertEquals(dinner.getActName(), d.getPlan().get(dinner.getStart()).getActName());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
