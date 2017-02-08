import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by sher on 2017/1/29 0029.
 */
class DecomposeTest {
    @Test
    public void test1() {
        Decompose d = new Decompose();
        long n = 60000000;
        assertEquals("1 2 3 7 9", d.decompose(10));
//        assertEquals(" ",d.decompose(12345));
    }
}