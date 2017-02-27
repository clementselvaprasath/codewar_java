import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by sher on 13/2/2017.
 */
class MorseCodeDecoderTest {
    @Test
    public void test() {
//        MorseCodeDecoder.decode(".... . -.--   .--- ..- -.. .");
        MorseCodeDecoder.decode(".");
    }

    @Test
    public void test2() {
//        MorseCodeDecoder.decodeBits("1100110011001100000011000000111111001100111111001111110000000000000011001111110011111100111111000000110011001111110000001111110011001100000011");
//        MorseCodeDecoder.stripZero("0011010101001000");
        MorseCodeDecoder.decodeBits("01110");
        assertEquals(1, MorseCodeDecoder.findUnitLen("1110111"));
        assertEquals(".", MorseCodeDecoder.decodeBits("01110"));
        assertEquals(3, MorseCodeDecoder.findUnitLen("111"));
        assertEquals(2, MorseCodeDecoder.findUnitLen("1100110011001100000011000000111111001100111111001111110000000000000011001111110011111100111111000000110011001111110000001111110011001100000011"));

        int i = 3;
    }

}