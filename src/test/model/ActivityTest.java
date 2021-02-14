package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// test class for Activity
public class ActivityTest {
    private Activity testActivity;

    @BeforeEach
    public void runBefore() {
        testActivity = new Activity("Test", 0, 1);
    }

    @Test
    public void testActConstructor() {
        assertEquals("Test", testActivity.getActName());
        assertEquals(0, testActivity.getStart());
        assertEquals(1, testActivity.getEnd());
    }

}
