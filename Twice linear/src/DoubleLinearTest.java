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
        testing(DoubleLinear_best.dblLinear(10), 22);
        testing(DoubleLinear_best.dblLinear(20), 57);
        testing(DoubleLinear_best.dblLinear(30), 91);
        testing(DoubleLinear_best.dblLinear(50), 175);
        testing(DoubleLinear.dblLinear(60000), 1511311);

    }
}