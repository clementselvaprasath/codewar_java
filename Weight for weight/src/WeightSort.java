import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class WeightSort {
    public static String orderWeight(String strng) {
        List<String> weightArr = Arrays.asList(strng.split(" "));
        weightArr.sort(new weightComparator());
        return String.join(" ",weightArr);
    }

    static class weightComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            String lhs[] = o1.split("");
            String rhs[] = o2.split("");
            System.out.println("the array are: ");
            for (String i:lhs) {
                System.out.print(i+" ");
            }
            System.out.println();
            Integer lWeight = Arrays.stream(lhs).mapToInt(l -> Integer.parseInt(l)).sum();
            Integer rWeight = Arrays.stream(rhs).mapToInt(l -> Integer.parseInt(l)).sum();
            System.out.println(lWeight + "  " + rWeight);

            if (lWeight.equals(rWeight)){
                return o1.compareTo(o2);
            }else{
                return lWeight.compareTo(rWeight);
            }
        }
    }
}
