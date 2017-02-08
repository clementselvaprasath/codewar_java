import javafx.util.Pair;

import java.lang.reflect.Parameter;
import java.util.*;

/**
 * Created by sher on 2017/2/6 0006.
 */
public class test {
    public static void main(String[] argv) {
        Pair<Integer, Integer> p = new Pair<>(1, 1);
        List<Pair<Integer, Integer>> l = new ArrayList<>();
        l.add(new Pair<>(1, 2));

        int[][] added = new int[5][5];
        boolean b;
        System.out.println(b);
        System.out.println(added[1][1]);
        //        l.removeIf(P -> P.equals(new Pair<>(1, 1)));
//        System.out.println(p.equals(new Pair<>(1, 1)));
//        System.out.println(l.size());
//        l.forEach(l::remove);
//        System.out.println(l.size());
    }
}
