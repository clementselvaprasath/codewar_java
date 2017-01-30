/**
 * Created by sher on 2017/1/26 0026.
 */

import java.util.ArrayList;
import java.util.List;

public class Johnann {

    private static List<Long> John = new ArrayList<>();
    private static List<Long> Ann = new ArrayList<>();

    static {
        John.add(0L);
        Ann.add(1L);
    }

    private static boolean isEnough(List<Long> target, Long len) {
        return target.size() >= len;
    }


    private static void computeNthDay(List<Long> lhs, List<Long> rhs, Long day) {
        if (lhs.size() > day)
            return;

        int dayStart = lhs.size() - 1;
        if (day - dayStart > 1) {
            for (int i = dayStart; i <= day; ++i) {
                computeNthDay(lhs, rhs, (long) i);
            }
        } else {
            Long lhsLastDayDone = lhs.get(dayStart);
            computeNthDay(rhs, lhs, lhsLastDayDone);
            lhs.add(day - rhs.get(lhsLastDayDone.intValue()));
        }
    }

    private static List<Long> dirFetch(List<Long> target, long n) {
        return target.subList(0, (int) n);
    }

    private static long sumKata(List<Long> name, Long n) {
        return name.subList(0, (int) n.longValue()).stream().mapToLong(l -> l.longValue()).sum();
    }

    public static List<Long> john(long n) {
        if (isEnough(John, n))
            return dirFetch(John, n);

        for (int i = John.size(); i < n; ++i) {
            computeNthDay(John, Ann, (long) i);
        }
        return dirFetch(John, n);
    }

    public static List<Long> ann(long n) {
        if (isEnough(Ann, n))
            return dirFetch(Ann, n);

        for (int i = Ann.size(); i < n; ++i) {
            computeNthDay(Ann, John, (long) i);
        }
        return dirFetch(Ann, n);
    }

    public static long sumJohn(long n) {
        computeNthDay(John, Ann, n);
        return sumKata(John, n);
    }

    public static long sumAnn(long n) {
        computeNthDay(Ann, John, n);
        return sumKata(Ann, n);
    }
}