package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DayTest {
    private Day testDay;
    private Activity testSleep;
    private Activity testWorkout;
    private Activity testMath101;
    private Activity testStudy;
    private Activity testCpsc121;
    private Activity testCpsc210;

    @BeforeEach
    public void runBefore () {
        testSleep = new Activity("Sleep", 0, 9);
    }
}
