/**
 * Created by sher on 2017/1/29 0029.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class Decompose {

    private long remain;
    private Stack<Long> squares;
    private long initial;

    public Decompose() {
        squares = new Stack<>();
    }

    private void initFields(long n) {
        initial = n;
        remain = n * n;
    }

    public String decompose(long n) {
        initFields(n);
        if (processNext()) {
            return returnResultString();
        }
        return "";
    }

    private String returnResultString() {
        List<String> result = new ArrayList<>();
        squares.forEach(l -> result.add(0, l.toString()));
        return String.join(" ", result);
    }

    private boolean processNext() {
        long side = getNextNum(); // side is the biggest number that we can use currently

        long area = side * side;

        if (area == remain) {
            squares.push(side);
            return true;
        }
        else {
            long remain_buf = remain;
            long side_buf = side;
            long area_buf = area;
            while (side_buf > 0) {
                squares.push(side_buf);
                remain -= area_buf;
                if (!processNext()) {  // try to go on as
                    --side_buf;
                    area_buf = side_buf * side_buf;  // maintain: squared
                    squares.pop();
                    remain = remain_buf;
                } else
                    return true;   // found solution
            }
            return false;
        }
    }

    private long getNextNum() {
        long side = (long) Math.sqrt(remain);
        if (side == initial)
            --side;
        side = makeDecreaseSequence(side);
        return side;
    }

    private long makeDecreaseSequence(long square) {
        while (!squares.empty() && square >= squares.lastElement())
            --square;
        return square;
    }

}
