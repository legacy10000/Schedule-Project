package model;

import exceptions.BeginStopRangeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// test class for Activity
public class ActivityTest {
    private Activity testActivity;

    @Test
    public void testActConstructorNoException() {
        try {
            testActivity = new Activity("Test", 0, 1);
        } catch (BeginStopRangeException e) {
            fail();
        }
        assertEquals("Test", testActivity.getActName());
        assertEquals(0, testActivity.getStart());
        assertEquals(1, testActivity.getEnd());
    }

    @Test
    public void testActConstructorExceptionThrownBeginBigger() {
        try {
            testActivity = new Activity("Test", 12, 4);
            fail();
        } catch (BeginStopRangeException e) {
            assertNull(testActivity);
        }
    }

    @Test
    public void testActConstructorExceptionThrownNeagtives() {
        try {
            testActivity = new Activity("Test", -14, -1);
            fail();
        } catch (BeginStopRangeException e) {
            // Good
        }
        assertNull(testActivity);
    }

    @Test
    public void testActConstructorExceptionThrownTooLarge() {
        try {
            testActivity = new Activity("Test", 24, 25);
            fail();
        } catch (BeginStopRangeException e) {
            assertNull(testActivity);
        }
    }

    @Test
    public void testActConstructorExceptionThrownStop0() {
        try {
            testActivity = new Activity("Test", 0, 0);
            fail();
        } catch (BeginStopRangeException e) {
            assertNull(testActivity);
        }
    }

}
