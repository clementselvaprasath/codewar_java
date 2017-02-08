import java.util.Arrays;
import java.util.List;

/**
 * Created by sher on 2017/2/5 0005.
 */
public class User {
    public int rank = -8;
    public int progress = 0;
    private static final List _rank;

    static {
        _rank = Arrays.asList(-8, -7, -6, -5, -4, -3, -2, -1, 1, 2, 3, 4, 5, 6, 7, 8);
    }

    public int incProgress(int i) throws Exception {
        if (!validateRank(i))
            throw new IllegalArgumentException("rank not valid: " + i);

        int curRankPos = _rank.indexOf(rank);
        int completeRankPos = _rank.indexOf(i);

        upgrade(calcProgress(curRankPos, completeRankPos));


        return 10;
    }

    private void upgrade(int p) {
        if (rank == 8)
            return;

        progress += p;
        while(progress >= 100) {
            rank = (int) _rank.get(_rank.indexOf(rank) + 1);
            progress -= 100;
            if (rank == 8){
                progress = 0;
                return;
            }
        }
    }

    private int calcProgress(int curRankPos, int completeRankPos) {
        int dis = completeRankPos - curRankPos;

        if (dis > 0) {
            return 10 * dis * dis;
        } else {
            switch (dis) {
                case 0:
                    return 3;
                case -1:
                    return 1;
                default:
                    return 0;
            }
        }

    }

    private boolean validateRank(int i) {
        return _rank.contains(i);
    }
}
