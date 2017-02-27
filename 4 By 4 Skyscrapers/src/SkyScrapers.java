import java.util.*;

/**
 * Created by sher on 26/2/2017.
 * four hours or so
 */
public class SkyScrapers {
    private static int SIZE = 4;
    private static Map<Integer, Map<Integer, List<int[]>>> combiTB;
    private static int[] clue;

    static int[][] solvePuzzle(int[] clues) {
        SIZE = clues.length / 4;
        clue = clues;

        buildCombinationTB();

        List<int[]> answer = findSolution();
        int[][] a = new int[SIZE][];
        for (int i = 0; i < SIZE; i++) {
            a[i] = answer.get(i);
        }
        return a;
    }

    static List<int[]> findSolution() {
        List<int[]> answer = new ArrayList<>(4);
        for (int i = 0; i < SIZE; i++) {
            answer.add(null);
        }
        goFindIt(answer, -1);
        answer.forEach(
                x -> System.out.println(Arrays.toString(x))
        );
        return answer;
    }

    private static boolean goFindIt(List<int[]> answer, int rowIndex) {
        if (!verifyCurrent(answer, rowIndex))
            return false;
        if (rowIndex == SIZE - 1)
            return true;

        int[] pair = getPair(rowIndex + 1, true);

        List<int[]> allPossible = getAllPossibleArr(pair);


        for (int[] arr : allPossible) {
            answer.set(rowIndex + 1, arr);
            if (goFindIt(answer, rowIndex + 1))
                return true;   // if find any, end recursion
        }
        return false;  // can't find any Answer
    }

    private static List<int[]> getAllPossibleArr(int[] pair) {
        List<int[]> allPossible = new ArrayList<>();
        if (pair[0] == 0 || pair[1] == 0) {
            if (pair[0] == 0) {
                for (int i = 1; i <= SIZE; i++) {
                    if (pair[1] == 0) {
                        combiTB.get(i).forEach(
                                (integer, ints) -> allPossible.addAll(ints)
                        );
                    } else {
                        System.out.println(i + " " + pair[1]);
                        allPossible.addAll(combiTB.get(i).getOrDefault(pair[1], new ArrayList<>()));
                    }
                }
            } else {
                combiTB.get(pair[0]).forEach(
                        (integer, ints) -> ints.forEach(allPossible::add)
                );
            }
        } else {
            allPossible.addAll(combiTB.get(pair[0]).getOrDefault(pair[1], new ArrayList<>()));
        }
        return allPossible;
    }

    private static boolean verifyCurrent(List<int[]> answer, int rowIndex) {
        if (rowIndex == -1)
            return true;
        Outer:
        for (int i = 0; i < SIZE; i++) {
            int[] pair = getPair(i, false);
            Inner:
            for (int[] column : getAllPossibleArr(pair)) {
                for (int j = 0; j <= rowIndex; j++) {
                    if (column[j] != answer.get(j)[i])
                        continue Inner;   // if one number doesn't match, get to next candidate
                }
                continue Outer; // only if all row satisfy the current answer, can we move on to test next column
            }
            return false;
        }
        return true;
    }

    private static int[] getPair(int index, boolean horizontal) {
        if (horizontal)
            return new int[]{clue[SIZE * 4 - index - 1], clue[SIZE + index]};
        else
            return new int[]{clue[index], clue[SIZE * 3 - 1 - index]};
    }

    static void buildCombinationTB() {
        combiTB = new HashMap<>();
        List<int[]> combi = getAllCombination();
        combi.forEach(
                seq -> {
                    int[] clue = getClue(seq);
                    combiTB.computeIfAbsent(clue[0], k -> new HashMap<>()).computeIfAbsent(clue[1], k -> new ArrayList<>()).add(seq);
                }
        );
//        combiTB.forEach(
//                (integer, integerListMap) -> integerListMap.forEach(
//                        (integer1, ints) -> ints.forEach(
//                                seq -> System.out.println(integer + ": " + integer1 + " " + Arrays.toString(seq))
//                        )
//                )
//        );
    }

    static List<int[]> getAllCombination() {
        List<int[]> combi = new ArrayList<>();
        genCombi(combi, null, 1);
//        combi.forEach(arr -> System.out.println(Arrays.toString(arr)));
        return combi;
    }

    static void genCombi(List<int[]> combi, int[] res, int index) {
        if (res == null) {
            res = new int[SIZE];
            for (int i = 0; i < SIZE; i++) {
                res[i] = -1;
            }
        }

        for (int i = 0; i < SIZE; i++) {
            if (res[i] == -1) {
                res[i] = index;
                if (index == SIZE) {
                    combi.add(res.clone());
                    res[i] = -1;
                } else {
                    genCombi(combi, res, index + 1);
                    res[i] = -1;
                }
            }

        }

    }

    static int[] getClue(int[] row) {

        int frontClue = 0;
        int frontMaxScrapers = 0;
        for (int i = 0; i < row.length; i++) {
            if (row[i] > frontMaxScrapers) {
                frontClue++;
                frontMaxScrapers = row[i];
            }
        }

        int backClue = 0;
        int backMaxScrapers = 0;
        for (int i = row.length - 1; i >= 0; i--) {
            if (row[i] > backMaxScrapers) {
                backClue++;
                backMaxScrapers = row[i];
            }
        }
//        System.out.println(frontClue + " " + backClue);
        return new int[]{frontClue, backClue};
    }

}
