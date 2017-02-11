import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by sher on 2017/2/5 0005.
 */
class ConwayLifeTest {
    @Test
    public void testGlider() {
        int[][][] gliders = {
                {
                        {1, 1, 1, 0, 0, 0, 1, 0},
                        {1, 0, 0, 0, 0, 0, 0, 1},
                        {0, 1, 0, 0, 0, 1, 1, 1}
                },

                {
                        {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
                        {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0,},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1,}
                }
        };
        assertTrue(isEqual(gliders[1], ConwayLife.getGeneration(gliders[0], 16)));
    }

    @Test
    public void test2() {
        int[][][] gliders = {
                {
                        {1, 0, 0},
                        {0, 1, 1},
                        {1, 1, 0}
                },
                {
                        {0, 1, 0},
                        {0, 0, 1},
                        {1, 1, 1}
                }
        };
        assertTrue(isEqual(gliders[1], ConwayLife.getGeneration(gliders[0], 1)));
    }

    private static void drawArr(int[][] arr) {
        if (arr == null) {
            System.out.println("null array");
            return;
        }

        for (int i = 0; i != arr.length; ++i) {
            for (int ii = 0; ii != arr[i].length; ++ii) {
                System.out.print(arr[i][ii] + " ");
            }
            System.out.println();
        }

    }

    private static boolean isEqual(int[][] lhs, int[][] rhs) {
        for (int i = 0; i < lhs.length; i++) {
            for (int j = 0; j < lhs[0].length; j++) {
                if (lhs[i][j] != rhs[i][j])
                    return false;
            }
        }
        return true;
    }

}