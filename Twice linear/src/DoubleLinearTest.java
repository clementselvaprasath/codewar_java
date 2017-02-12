import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by sher on 12/2/2017.
 */


public class DoubleLinearTest {

    private static void testing(int actual, int expected) {
        assertEquals(expected, actual);
    }

    @Test
    public void test() {
        System.out.println("Fixed Tests dblLinear");
        testing(DoubleLinear.dblLinear(10), 22);
        testing(DoubleLinear.dblLinear(20), 57);
        testing(DoubleLinear.dblLinear(30), 91);
        testing(DoubleLinear.dblLinear(50), 175);
        testing(DoubleLinear.dblLinear(60000), 175);

    }
}