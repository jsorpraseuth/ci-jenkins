import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ClockTest {

    @Test
    public void simpleTest() {
        int a = 1;
        int b = 2;
        assertTrue(a + b == 3);
    }

    @Test
    public void simpleTestFail() {
        // force fail again
        //int a = 2;
        int a = 1;
        int b = 2;
        assertTrue(a + b == 4);
    }

}
