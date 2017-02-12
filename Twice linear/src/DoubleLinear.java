import apple.laf.JRSUIUtils;

import java.util.*;

/**
 * Created by sher on 12/2/2017.
 */
public class DoubleLinear {
    private static TreeSet<Integer> li = new TreeSet<>();
    private static int curPos = 0;
    private static int tarIndex = 0;
    private static long get_time = 0;
    private static long now = 0;
    private static long total_time = 0;

    static {
        li.add(1);
    }

    public static int dblLinear(int n) {
        get_time = 0;
        total_time = System.currentTimeMillis();
        System.out.println("target: " + n);
        tarIndex = n;
        if (curPos < n) {
            calcTo(n);
        }

        total_time = System.currentTimeMillis() - total_time;
        System.out.println(li.size() + " " + get_time / 1000 + " totalTime :" + total_time / 1000);
        return getAt(n);
    }

    private static int linear1(int in) {
        return 2 * in + 1;
    }

    private static int linear2(int in) {
        return 3 * in + 1;
    }

    private static void calcTo(int end) {

        while (curPos < end) {
            int num = getAt(curPos);
            li.add(linear1(num));
            li.add(linear2(num));
//            li.sort(Integer::compareTo);
            ++curPos;
            if (tarIndex < li.size()) {
                if (linear1(num) > getAt(tarIndex))
                    return;
            }
        }

    }

    private static int getAt(int index) {
//        return li.get(index);
        long sTime = System.currentTimeMillis();
        Iterator<Integer> ite = li.iterator();
        int i = index;
        int result = 0;
        while (i >= 0) {
            result = ite.next();
            --i;
        }

        long elaps = (System.currentTimeMillis() - sTime);
        get_time += elaps;
        return result;
    }
}

