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

        HashSet<Integer> s = new HashSet<>();
        s.add(3);
        System.out.println(s.contains(3));

        testClass t = new testClass();
        t.print();
        testClass.p();

        //        l.removeIf(P -> P.equals(new Pair<>(1, 1)));
//        System.out.println(p.equals(new Pair<>(1, 1)));
//        System.out.println(l.size());
//        l.forEach(l::remove);
//        System.out.println(l.size());
    }
}
