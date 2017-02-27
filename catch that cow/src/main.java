import java.util.TreeSet;

/**
 * Created by sher on 25/2/2017.
 * POJ 3278 Catch That Cow
 */
class main {
    static int des;
    static TreeSet<Integer> tree = new TreeSet<>();

    public static int main(String[] args) {
        return getCow(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    }

    static int getCow(int start, int end) {
        des = start;
        tree.clear();
        step(end, 0);
        System.out.println(tree);
        return tree.first();
    }

    static void step(int cur, int total) {
//        System.out.println(cur);

        // reach the end
        if (cur == des) {
            tree.add(total);
            return;
        }

        // if the first move is odd value, make it even
        if (cur % 2 != 0) {
            step(cur + 1, total + 1);
            step(cur - 1, total + 1);
            return;
        }


        int buf = cur / 2;
        if (buf < des) {
            // near the end, compare total three method, choose the smallest path
            tree.add(total + min(cur - des, des * 2 - cur + 1, cur - buf + 1));
        } else {
            step(cur / 2, total + 1);
        }
    }

    static int min(int... move) {
        TreeSet<Integer> tree = new TreeSet<>();
        for (int i : move) {
            tree.add(i);
        }
        return tree.first();
    }
}
