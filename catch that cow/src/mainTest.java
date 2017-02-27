import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by sher on 25/2/2017.
 */
class mainTest {

    @Test
    public void test() {
        assertEquals(4, main.main(new String[]{"5", "17"}));
//        assertEquals(1, main.min(4, 6, 1));
        assertEquals(45, main.getCow(124, 666));
        assertEquals(48, main.getCow(789, 6666));
    }

}