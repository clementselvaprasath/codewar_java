import javafx.util.Pair;

import java.util.*;

/**
 * Created by sher on 2017/2/5 0005.
 */
public class ConwayLife {

    private static ArrayList<ArrayList<Integer>> gameboard; // this is really bad, violate code to interface
    private static List<Pair<Integer, Integer>> aliveCells;
    private static List<Pair<Integer, Integer>> affectedCells = new ArrayList<>();

    public static int[][] getGeneration(int[][] glider, int generation) {
//        draw(glider, generation);
        init_gameboard(glider);
        init_aliveCells();
//        System.out.println("initial board");
//        drawBoard();
        while (generation > 0) {
            envole();
            --generation;
//            System.out.println("after generation");
//            drawBoard();
        }
        int[][] res = getResult();
//        draw(res, -1);
        return res;
    }

    private static void draw(int[][] glider, int generation) {
        System.out.println("gen: " + generation);
        Arrays.stream(glider).forEach(
                x -> {
                    Arrays.stream(x).forEach(
                            y -> {
                                System.out.print(y + " ");
                            }
                    );
                    System.out.println();
                }
        );
    }

    private static int[][] getResult() {
        int up = 0,
                down = gameboard.size() - 1,
                left = 0,
                right = gameboard.get(0).size() - 1;

        while (up <= down) {
            if (!isRowEmpty(up))
                break;
            ++up;
        }

        if (up == down + 1)
            return null;

        while (down != up) {
            if (!isRowEmpty(down))
                break;
            --down;
        }

        while (left <= right) {
            if (!isColumnEmpty(left))
                break;
            ++left;
        }

        while (right != left) {
            if (!isColumnEmpty(right))
                break;
            --right;
        }

        int[][] res = new int[down - up + 1][right - left + 1];
        for (int row = up; row <= down; row++) {
            for (int col = left; col <= right; col++) {
                res[row - up][col - left] = gameboard.get(row).get(col);
            }
        }
        return res;

    }

    private static void drawBoard() {
        gameboard.forEach(row -> {
            row.forEach(
                    cell -> {
                        System.out.format(" %2d ", cell);
                    }
            );
            System.out.println();
        });
    }

    private static void init_aliveCells() {
        aliveCells = new ArrayList<>();
        int lastRow = gameboard.size() - 1;
        int lastColumn = gameboard.get(0).size() - 1;
//        gameboard.stream().filter(x -> gameboard.indexOf(x) > 0 && gameboard.indexOf(x) < lastRow)
//                .forEach(
//                        row -> row.stream().filter(cell -> row.indexOf(cell) > 0 && row.indexOf(cell) < lastColumn)
//                                .forEach(cell -> {
//                                    if (isAlive(cell)) {
//                                        aliveCells
//                                    }
//                                })
//                );

        for (int row = 1; row < lastRow; row++) {
            for (int col = 1; col < lastColumn; col++) {
                if (isAlive(gameboard.get(row).get(col))) {
                    aliveCells.add(new Pair<>(row, col));
                }
            }
        }
//        System.out.println("alive cells: " + aliveCells.size());

    }

    private static boolean isAlive(Integer i) {
        return (i & 1) == 1;
    }

    private static boolean isAlive(Pair<Integer, Integer> cell) {
        return isAlive(gameboard.get(cell.getKey()).get(cell.getValue()));
    }

    private static void envole() {
        affectedCells.clear();
        updateAliveCellNeighbor();
//        System.out.println("after update " + affectedCells.size());
//        drawBoard();
        checkAffectedCells();
        updateBorder();
    }

    private static void updateBorder() {
        boolean up = false, down = false, left = false, right = false;
        if (!isRowEmpty(0)) {
            up = true;
            for (int i = 0; i < aliveCells.size(); i++) {
                Pair<Integer, Integer> oriCell = aliveCells.get(i);
                aliveCells.set(i, new Pair<>(oriCell.getKey() + 1, oriCell.getValue()));
            }

        }

        if (!isRowEmpty(gameboard.size() - 1))
            down = true;

        if (!isColumnEmpty(0)) {
            left = true;
            for (int i = 0; i < aliveCells.size(); i++) {
                Pair<Integer, Integer> oriCell = aliveCells.get(i);
                aliveCells.set(i, new Pair<>(oriCell.getKey(), oriCell.getValue() + 1));
            }
        }

        if (!isColumnEmpty(gameboard.get(0).size() - 1))
            right = true;

        wrap_gameboard(up, down, left, right);
    }

    private static boolean isColumnEmpty(int col) {
        for (List<Integer> row : gameboard) {
            if (row.get(col) != 0)
                return false;
        }
        return true;
    }

    private static boolean isRowEmpty(int row) {

        ArrayList<Integer> cells = gameboard.get(row);
        for (Integer i : cells) {
            if (i != 0)
                return false;
        }
        return true;
    }

    private static void checkAffectedCells() {
        List<Pair<Integer, Integer>> cellToDelete = new ArrayList<>();
        List<Pair<Integer, Integer>> cellToAdd = new ArrayList<>();

        affectedCells.forEach(
                cell -> {
                    if (isAlive(cell)) {
                        int neighborNum = getNeighborNumber(cell);

                        if (neighborNum < 2 || neighborNum > 3) {
                            cellToDelete.add(cell);
                            cellDie(cell);
                        } else {
                            cellStay(cell);
                        }
                    } else {
                        if (getNeighborNumber(cell) == 3) {
                            cellAlive(cell);
                            cellToAdd.add(cell);
                        } else
                            cellStay(cell);
                    }
                }
        );

        cellToDelete.forEach(cell -> aliveCells.remove(cell));
        cellToAdd.forEach(cell -> aliveCells.add(cell));
    }

    private static void cellAlive(Pair<Integer, Integer> cell) {
//        System.out.println("" + cell.getKey() + " " + cell.getValue() + " lives");
        gameboard.get(cell.getKey()).set(cell.getValue(), 1);
//        drawBoard();
    }

    private static void cellStay(Pair<Integer, Integer> cell) {
//        System.out.println("" + cell.getKey() + " " + cell.getValue() + " stays");
        int oriVal = gameboard.get(cell.getKey()).get(cell.getValue());
        gameboard.get(cell.getKey()).set(cell.getValue(), oriVal & 1);
//        drawBoard();
    }

    private static void cellDie(Pair<Integer, Integer> cell) {
//        System.out.println("" + cell.getKey() + " " + cell.getValue() + " dies");
        gameboard.get(cell.getKey()).set(cell.getValue(), 0);
//        drawBoard();
    }

    private static int getNeighborNumber(Pair<Integer, Integer> cell) {
        return gameboard.get(cell.getKey()).get(cell.getValue()) / 16;
    }

    private static void updateAliveCellNeighbor() {
        int[][] added = new int[gameboard.size()][gameboard.get(0).size()];
        for (Pair<Integer, Integer> cell : aliveCells) {
            int row = cell.getKey();
            int col = cell.getValue();
            addNeighbor(row - 1, col - 1, added);
            addNeighbor(row - 1, col, added);
            addNeighbor(row - 1, col + 1, added);
            addNeighbor(row, col - 1, added);
            addNeighbor(row, col + 1, added);
            addNeighbor(row + 1, col - 1, added);
            addNeighbor(row + 1, col, added);
            addNeighbor(row + 1, col + 1, added);
        }
    }

    private static void addNeighbor(int row, int col, int[][] added) {
//        System.out.println(row + ":" + col);
        gameboard.get(row).set(col, gameboard.get(row).get(col) + 16);
        if (added[row][col] == 0) {
            added[row][col] = 1;
            affectedCells.add(new Pair<>(row, col));
        }

    }

    private static void init_gameboard(int[][] glider) {
        gameboard = new ArrayList<>(glider.length + 2);

        for (int[] aGlider : glider) {
            ArrayList<Integer> row = new ArrayList<>();
            Arrays.stream(aGlider).forEach(row::add);
            gameboard.add(row);
        }

        wrap_gameboard(true, true, true, true);
    }

    private static void wrap_gameboard(boolean up, boolean down, boolean left, boolean right) {
        if (up) {
            gameboard.add(0, getNewRow(gameboard.get(0).size()));
        }

        if (down) {
            gameboard.add(getNewRow(gameboard.get(0).size()));
        }

        if (left) {
            gameboard.forEach(x -> x.add(0, 0));
        }

        if (right) {
            gameboard.forEach(x -> x.add(0));
        }
    }

    private static ArrayList<Integer> getNewRow(int len) {
        ArrayList<Integer> newRow = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            newRow.add(0);
        }
        return newRow;
    }


}
