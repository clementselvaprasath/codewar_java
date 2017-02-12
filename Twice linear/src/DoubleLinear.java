import java.util.*;

/**
 * Created by sher on 12/2/2017.
 * <p>
 * holly fuck, spend quite a lot time optimization,
 * and got the wrong time messure solution
 */
public class DoubleLinear {
    private static SortedSet<Integer> li = new TreeSet<>();
    private static int curPos;

    static {
        li.add(1);
    }

    public static int dblLinear(int n) {
        initialize(n);
        calcTo(n);
        System.out.println(li.size());
        return li.first();

    }

    private static void initialize(int n) {
        li.clear();
        li.add(1);
        curPos = 0;
    }

    private static int linear1(int in) {
        return 2 * in + 1;
    }

    private static int linear2(int in) {
        return 3 * in + 1;
    }

    private static void calcTo(int end) {

        while (curPos < end) {
            int curNum = li.first();
            addToSet(linear1(curNum));
            addToSet(linear2(curNum));
            removeFirst();
            ++curPos;
        }
    }

    private static void removeFirst() {
        li.remove(li.first());
    }

    private static void addToSet(int i) {
        li.add(i);
    }

}

