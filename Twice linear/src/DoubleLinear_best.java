/**
 * Created by sher on 12/2/2017.
 */

import java.util.*;

class DoubleLinear_best {

    public static int dblLinear(int n) {
        if (n == 0) return 1;
        SortedSet<Integer> u = new TreeSet<>();
        u.add(1);
        for (int i = 0; i < n; i++) {
            int x = u.first();
            u.add(x * 2 + 1);
            u.add(x * 3 + 1);
            u.remove(x);
        }
        System.out.println(u.size());
        return u.first();
    }

}
