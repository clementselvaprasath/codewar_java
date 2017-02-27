import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by sher on 2017/2/5 0005.
 */
class UserTest {
    @Test
    public void testSomething() throws Exception {
        User user = new User();
        assertEquals(-8, user.rank);
        System.out.println(1);

        assertEquals(0, user.progress);
        System.out.println(2);

        user.incProgress(-7);
        assertEquals(10, user.progress);
        System.out.println(3);

        user.incProgress(-5); // will add 90 progress
        assertEquals(0, user.progress);
        System.out.println(4);

        assertEquals(-7, user.rank);
        System.out.println(5);

    }
}