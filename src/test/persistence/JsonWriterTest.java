package persistence;

import model.Day;
import model.Activity;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Day d = new Day("Monday", "Blues");
            JsonDayWriter writer = new JsonDayWriter("./data/my\0illegal:fileName.json");
            writer.openFile();
            fail("The IOException was expected");
        } catch (IOException e) {
            // Yaya good job!!!
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

}
