import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by sher on 27/3/2017.
 */
public class IntPartTest {
    @Test
    public void Numbers_Small() {
        assertEquals("Range: 1 Average: 1.50 Median: 1.50", IntPart.part(2));
        assertEquals("Range: 2 Average: 2.00 Median: 2.00", IntPart.part(3));
        assertEquals("Range: 3 Average: 2.50 Median: 2.50", IntPart.part(4));
        assertEquals("Range: 5 Average: 3.50 Median: 3.50", IntPart.part(5));
    }

    @Test
    public void partitionTest() {
        IntPart.part(35);
//        buf.forEach(
//                l -> System.out.println(l + "\n")
//        );
//        System.out.println(IntPart.prod(buf));
        assertEquals(7, IntPart.partition(5).size());
        assertEquals(5, IntPart.partition(4).size());
    }

    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>(Arrays.asList(3, 4));
        List<Integer> n = new ArrayList<>(l);
        n.add(333);
        System.out.println(l);
        System.out.println(n);
    }
}