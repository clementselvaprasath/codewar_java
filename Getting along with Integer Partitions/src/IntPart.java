import java.util.*;

/**
 * Created by sher on 27/3/2017.
 */
public class IntPart {
    public static String part(long n) {
        Set<List<Long>> res = partition(n);
        TreeSet<Double> multi = prod(res);
        return buildResString(multi);
    }

    private static String buildResString(TreeSet<Double> multi) {
        Object[] l = multi.toArray();
        double ave = 0;
        double mid;
        for (Object d : l) {
            ave += (Double) d;
        }
        mid = l.length % 2 == 1 ? ((Double) l[l.length / 2]) : (((Double) l[l.length / 2 - 1] + (Double) l[l.length / 2]) / 2);
        return String.format("Range: %1$d Average: %2$.2f Median: %3$.2f", (int) ((Double) l[l.length - 1] - (Double) l[0]), ave / l.length, mid);
    }

    static TreeSet<Double> prod(Set<List<Long>> res) {
        TreeSet<Double> multiRes = new TreeSet<>();
        res.forEach(
                subArray -> {
                    double fi = 1;
                    for (double d : subArray) {
                        fi *= d;
                    }
                    multiRes.add(fi);
                }
        );
        return multiRes;
    }

    private static Map<Long, Set<List<Long>>> memory = new HashMap<>();

    static Set<List<Long>> partition(long i) {
        Set<List<Long>> res = new HashSet<>();
        if (i == 0)
            return res;
        for (long j = 1; j <= i - j; j++) {
            Set<List<Long>> subSet = memory.containsKey(i - j) ? getCopy(memory.get(i - j)) : partition(i - j);
            long finalJ = j;
            subSet.forEach(
                    l -> {
                        l.add(finalJ);
                        l.sort(Long::compareTo);
                    }
            );
            res.addAll(subSet);
        }
//        final result add a self
        res.add(new ArrayList<>(Arrays.asList(i)));

        memory.put(i, getCopy(res));
        return res;
    }

    private static Set<List<Long>> getCopy(Set<List<Long>> lists) {
        Set<List<Long>> copy = new HashSet<>();
        lists.forEach(
                l -> {
                    List<Long> buf = new ArrayList<>(l);
                    copy.add(buf);
                }
        );
        return copy;
    }
}
