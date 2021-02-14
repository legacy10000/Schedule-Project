package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// test class for Day
public class DayTest {
    private Day testDay;
    private Activity testSleep;
    private Activity testWorkout;
    private Activity testMath101;
    private Activity testStudy;
    private Activity testCpsc121;
    private Activity testCpsc210;
    private Activity testFull;

    @BeforeEach
    public void runBefore () {
        testSleep = new Activity("Sleep Morning", 0, 9);
        testWorkout = new Activity("Workout", 9, 10);
        testMath101 = new Activity("MATH 101", 10, 12);
        testStudy = new Activity("Study", 10, 12);
        testCpsc121 = new Activity("CPSC 121", 2, 3);
        testCpsc210 = new Activity("CPSC 210", 3, 4);
        testFull = new Activity("FULL", 0, 23);
        testDay = new Day("Tuesday", "Test Plan");
    }

    @Test
    public void testConstructor() {
        assertEquals("Tuesday", testDay.getDayOfWeek());
        assertEquals("Test Plan", testDay.getPlanName());
    }

    @Test
    public void testAddActivitySmall() {
        testDay.addActivity(testCpsc121);
        assertEquals(testCpsc121.getActName(), testDay.getPlan().get(testCpsc121.getStart()).getActName());
        assertEquals("Available", testDay.getPlan().get(testCpsc121.getEnd()).getActName());
    }

    @Test
    public void testAddActivityLarge() {
        testDay.addActivity(testSleep);
        assertEquals(testSleep.getActName(), testDay.getPlan().get(testSleep.getStart()).getActName());
        assertEquals(testSleep.getActName(), testDay.getPlan().get((testSleep.getStart()+ 4)).getActName());
        assertEquals(testSleep.getActName(), testDay.getPlan().get((testSleep.getEnd()- 1)).getActName());
        assertEquals("Available", testDay.getPlan().get(testSleep.getEnd()).getActName());
    }

    @Test
    public void testAddActivityMany() {
        testDay.addActivity(testSleep);
        testDay.addActivity(testWorkout);
        testDay.addActivity(testCpsc210);
        assertEquals(testSleep.getActName(), testDay.getPlan().get(testSleep.getStart()).getActName());
        assertEquals(testSleep.getActName(), testDay.getPlan().get((testSleep.getStart()+ 4)).getActName());
        assertEquals(testWorkout.getActName(), testDay.getPlan().get(testWorkout.getStart()).getActName());
        assertEquals(testCpsc210.getActName(), testDay.getPlan().get(testCpsc210.getStart()).getActName());
    }

    @Test
    public void testRemoveActivity() {
        testDay.addActivity(testStudy);
        testDay.removeActivity(testStudy.getActName());
        assertEquals("Available", testDay.getPlan().get(testStudy.getStart()).getActName());
        assertEquals("Available", testDay.getPlan().get((testStudy.getStart() + 1)).getActName());
    }

    @Test
    public void testRemoveActivityMany() {
        testDay.addActivity(testMath101);
        testDay.addActivity(testSleep);
        testDay.addActivity(testCpsc210);
        testDay.removeActivity(testSleep.getActName());
        testDay.removeActivity(testMath101.getActName());
        testDay.removeActivity(testCpsc210.getActName());
        testDay.addActivity(testStudy);
        assertEquals(testStudy.getActName(), testDay.getPlan().get(testStudy.getStart()).getActName());
        assertEquals(testStudy.getActName(), testDay.getPlan().get((testStudy.getStart() + 1)).getActName());
        assertEquals("Available", testDay.getPlan().get(testCpsc210.getStart()).getActName());
        assertEquals("Available", testDay.getPlan().get(testSleep.getStart()).getActName());
        assertEquals("Available", testDay.getPlan().get((testSleep.getStart() + 6)).getActName());
        assertEquals("Available", testDay.getPlan().get((testSleep.getEnd()- 1)).getActName());
        assertEquals("Available", testDay.getPlan().get(testSleep.getEnd()).getActName());

    }

    @Test
    public void testClearSchedule() {
        testDay.addActivity(testFull);
        testDay.clearSchedule();
        assertEquals("Available", testDay.getPlan().get(0).getActName());
        assertEquals("Available", testDay.getPlan().get(12).getActName());
        assertEquals("Available", testDay.getPlan().get(23).getActName());
    }
}
