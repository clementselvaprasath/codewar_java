import java.util.*;

/**
 * Created by sher on 2017/2/5 0005.
 */
public class ConwayLife {

    private static ArrayList<ArrayList<Cell>> gameboard; // this is really bad, violate code to interface
    private static List<Cell> aliveCells;
    private static Set<Cell> affectedCells = new HashSet<>();

    private static class Cell {
        int row;
        int col;
        int state;

        public Cell() {
            row = 0;
            col = 0;
            state = 0;
        }

        public Cell(int r, int c) {
            setPos(r, c);
        }

        public Cell(int s) {
            setState(s);
        }

        void setPos(int r, int c) {
            row = r;
            col = c;
        }

        int getRow() {
            return row;
        }

        int getCol() {
            return col;
        }

        void setRow(int r) {
            row = r;
        }

        void setCol(int c) {
            col = c;
        }

        int getState() {
            return state;
        }

        void setState(int s) {
            state = s;
        }

        boolean isAlive() {
            return state == 1;
        }

        int getNeighborNum() {
            return state / 16;
        }

        void die() {
            state = 0;
        }

        void stay() {
            state &= 1;
        }

        void alive() {
            state = 1;
        }

        void addNeighbor() {
            state += 16;
        }
    }

    public static int[][] getGeneration(int[][] glider, int generation) {
//        draw(glider, generation);
        init_gameboard(glider);
        init_aliveCells();
        drawBoard();
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
                res[row - up][col - left] = gameboard.get(row).get(col).getState();
            }
        }
        return res;

    }

    private static void drawBoard() {
        gameboard.forEach(row -> {
            row.forEach(
                    cell -> {
                        System.out.format(" %2d ", cell.getState());
                    }
            );
            System.out.println();
        });
    }

    private static void init_aliveCells() {
        aliveCells = new ArrayList<>();
        int lastRow = gameboard.size() - 1;
        int lastColumn = gameboard.get(0).size() - 1;

        for (int row = 1; row < lastRow; row++) {
            for (int col = 1; col < lastColumn; col++) {
                Cell cell = gameboard.get(row).get(col);
                if (cell.isAlive()) {
                    aliveCells.add(cell);
                }
            }
        }
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

            aliveCells.forEach(
                    cell -> cell.setRow(cell.getRow() + 1)
            );
//            for (int i = 0; i < aliveCells.size(); i++) {
//                aliveCells.get(i);
//                oriCell.setRow(oriCell.getRow() + 1);
//                aliveCells.set(i, new Pair<>(oriCell.getKey() + 1, oriCell.getValue()));
        }

        if (!isRowEmpty(gameboard.size() - 1))
            down = true;

        if (!isColumnEmpty(0)) {
            left = true;
//            for (int i = 0; i < aliveCells.size(); i++) {
//                Pair<Integer, Integer> oriCell = aliveCells.get(i);
//                aliveCells.set(i, new Pair<>(oriCell.getKey(), oriCell.getValue() + 1));
//            }
            aliveCells.forEach(
                    cell -> cell.setCol(cell.getCol() + 1)
            );
        }


        if (!isColumnEmpty(gameboard.get(0).size() - 1))
            right = true;

        wrap_gameboard(up, down, left, right);
    }

    private static boolean isColumnEmpty(int col) {
        for (List<Cell> row : gameboard) {
            if (row.get(col).isAlive())
                return false;
        }
        return true;
    }

    private static boolean isRowEmpty(int row) {

        ArrayList<Cell> cells = gameboard.get(row);
        for (Cell i : cells) {
            if (i.isAlive())
                return false;
        }
        return true;
    }

    private static void checkAffectedCells() {
        List<Cell> cellToDelete = new ArrayList<>();
        List<Cell> cellToAdd = new ArrayList<>();

        affectedCells.forEach(
                cell -> {
                    if (cell.isAlive()) {
                        int neighborNum = cell.getNeighborNum();

                        if (neighborNum < 2 || neighborNum > 3) {
                            cellToDelete.add(cell);
                            cell.die();
                        } else {
                            cell.stay();
                        }
                    } else {
                        if (cell.getNeighborNum() == 3) {
                            cell.alive();
                            cellToAdd.add(cell);
                        } else
                            cell.stay();
                    }
                }
        );

        cellToDelete.forEach(cell -> aliveCells.remove(cell));
        cellToAdd.forEach(cell -> aliveCells.add(cell));
    }

    private static void updateAliveCellNeighbor() {
        for (Cell cell : aliveCells) {
            int row = cell.getRow();
            int col = cell.getCol();
            addNeighbor(row - 1, col - 1);
            addNeighbor(row - 1, col);
            addNeighbor(row - 1, col + 1);
            addNeighbor(row, col - 1);
            addNeighbor(row, col + 1);
            addNeighbor(row + 1, col - 1);
            addNeighbor(row + 1, col);
            addNeighbor(row + 1, col + 1);
        }
    }

    private static void addNeighbor(int row, int col) {
        System.out.println(row + ":" + col);
        Cell cell = gameboard.get(row).get(col);
        cell.addNeighbor();
        affectedCells.add(cell);
    }

    private static void init_gameboard(int[][] glider) {
        gameboard = new ArrayList<>(glider.length + 2);

        for (int[] aGlider : glider) {
            ArrayList<Cell> row = new ArrayList<>();
            for (int i = 0; i < aGlider.length; i++) {
               row.add(new Cell(aGlider[i]));
            }
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
            gameboard.forEach(x -> x.add(0, new Cell()));
        }

        if (right) {
            gameboard.forEach(x -> x.add(new Cell()));
        }
    }

    private static ArrayList<Cell> getNewRow(int len) {
        ArrayList<Cell> newRow = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            newRow.add(new Cell() );
        }
        return newRow;
    }
}
