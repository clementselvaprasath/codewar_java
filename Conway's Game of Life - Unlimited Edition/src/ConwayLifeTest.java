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
                        {0, 1, 0},
                        {0, 0, 1},
                        {1, 1, 1}
                }
        };
//        LifeDebug.print(gliders[0]);
        int[][] res = ConwayLife.getGeneration(gliders[0], 16);
//        assertEquals(res, gliders[1]);
        drawArr(res);
        System.out.println();
        drawArr(gliders[1]);
        assertTrue(res == gliders[1]);

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

}