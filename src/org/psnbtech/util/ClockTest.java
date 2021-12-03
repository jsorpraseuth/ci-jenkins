package src.org.psnbtech;

import org.junit.Assert;
import org.junit.Test;

public class ClockTest {

    Clock clock = new Clock(30);

    @Test
    public void testResetClock() {
        System.out.println("Running reset clock test")
        Assertions.assertEquals(0, clock.elapsedCycles, "Expected elapsed cycles did not match actual elapsed cycles.")
    }

    @Test
    public void testPaused() {
        // Comment to test CI and auto build on Jenkins
        System.out.println("Running paused test");;
        clock.setPaused(true);
        Assertions.assertEquals(true, clock.isPaused(), "Expected paused did not match actual paused.")
    }

}
