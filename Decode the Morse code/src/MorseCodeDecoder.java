import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sher on 13/2/2017.
 */

public class MorseCodeDecoder {
    private static int timeUnitLen = 0;
    private static String[] table = new String[]{"", ".", "", "-"};

    public static String decodeMorse(String morseCode) {
        return decode(morseCode);
    }

    static String decodeBits(String bits) {
        bits = stripZero(bits);
        timeUnitLen = findUnitLen(bits);

        List<String> morseCode = new ArrayList<>();
        Arrays.stream(bits.split(getRegex(timeUnitLen * 7))).forEach(
                word -> morseCode.add(
                        Arrays.stream(word.split(getRegex(timeUnitLen * 3))).map(
                                character -> (
                                        Arrays.stream(character.split(getRegex(timeUnitLen))).map(
                                                code -> {
                                                    if (code.charAt(0) == '1')
                                                        return table[code.length() / timeUnitLen];
                                                    else
                                                        return "";
                                                }
                                        ).filter(code -> code.length() > 0)
                                                .collect(Collectors.joining())
                                )
                        ).collect(Collectors.joining(" "))
                )
        );
        return String.join("   ", morseCode);
    }

    private static String getRegex(int i) {
        return String.format("0{%1$d}", i);
    }

    private static String stripZero(String bits) {
        if (bits.startsWith("0")) {
            bits = bits.split("^0+")[1];
        }
        if (bits.endsWith("0")) {
            bits = bits.split("0+$")[0];
        }
        return bits;
    }

    static int findUnitLen(String bits) {
        SortedSet<Integer> allLens = new TreeSet<>();
        char cur = bits.charAt(0);
        int curLen = 0;
        for (char c : bits.toCharArray()) {
            if (c != cur) {
                cur = c;
                allLens.add(curLen);
                if (allLens.size() >= 3)
                    break;

                curLen = 1;
            } else {
                ++curLen;
            }
        }
        allLens.add(curLen);
        return allLens.size() > 0 ? allLens.first() : -1;
    }

    static String decode(String morseCode) {
        String[] splited = splitCode(morseCode.trim());
        List<String> result = new ArrayList<>(splited.length);
        Arrays.stream(splited).forEach(
                code -> {
                    if (!code.equals(" ")) {
                        System.out.println(MorseCode.get(code));
                        result.add(MorseCode.get(code));
                    } else
                        result.add(" ");
                }
        );
        System.out.println(result.toString());
        return String.join("", result);
    }

    private static String[] splitCode(String code) {
        return code.split("((?<=[.-]) (?=\\W))|((?<=\\W) (?=[.-]))");
    }
}
