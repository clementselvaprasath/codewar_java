import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by sher on 26/2/2017.
 */
public class SkyScrapersTest {
    private static int clues[][] = {
            {
                    2, 2, 1, 3,
                    2, 2, 3, 1,
                    1, 2, 2, 3,
                    3, 2, 1, 3
            },
            {
                    0, 0, 1, 2,
                    0, 2, 0, 0,
                    0, 3, 0, 0,
                    0, 1, 0, 0
            }
    };

    private static int outcomes[][][] = {
            {
                    {1, 3, 4, 2},
                    {4, 2, 1, 3},
                    {3, 4, 2, 1},
                    {2, 1, 3, 4}},
            {
                    {2, 1, 4, 3},
                    {3, 4, 1, 2},
                    {4, 2, 3, 1},
                    {1, 3, 2, 4}}
    };

    @Test
    public void testSolvePuzzle1() {
        assertEquals(SkyScrapers.solvePuzzle(clues[0]), outcomes[0]);
    }
//
    @Test
    public void testSolvePuzzle2() {
        assertEquals(SkyScrapers.solvePuzzle(clues[1]), outcomes[1]);
    }

//    @Test
    public void tableTest() {
//        SkyScrapers.clue = new int[]{1, 2, 3, 4};
        SkyScrapers.solvePuzzle(clues[0]);
//        SkyScrapers.genCombi(new ArrayList<>(),3);
    }

//    @Test
    public void countClue() {
        assertTrue(Arrays.equals(SkyScrapers.getClue(new int[]{1, 2, 3, 4, 3}), new int[]{4, 2}));
        assertTrue(Arrays.equals(SkyScrapers.getClue(new int[]{1, 8, 3, 4, 3}), new int[]{2, 3}));
        assertTrue(Arrays.equals(SkyScrapers.getClue(new int[]{0, 0, 0}), new int[]{0, 0}));
    }

//    @Test
    public void sol(){
        SkyScrapers.findSolution();
    }
}