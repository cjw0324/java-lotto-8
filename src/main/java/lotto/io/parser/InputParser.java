package lotto.io.parser;

import static lotto.exception.ErrorMessage.NOT_VALID_INPUT_BLANK;
import static lotto.exception.ErrorMessage.NOT_VALID_STRING;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputParser {
    private static final char DELIMITER = ',';

    public List<Integer> parseNums(String rawNums) {
        blankCheck(rawNums);
        List<String> stringNums = Arrays.stream(rawNums.split(String.valueOf(DELIMITER))).toList();
        return convertList(stringNums);
    }

    public int parseNum(String rawNum) {
        return String2Int(rawNum.trim());
    }

    private static List<Integer> convertList(List<String> rawList) {
        List<Integer> result = new ArrayList<>();
        for (String rawString : rawList) {
            result.add(String2Int(rawString.trim()));
        }
        return result;
    }

    private static int String2Int(String s) {
        blankCheck(s);
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_VALID_STRING.print());
        }
    }

    private static void blankCheck(String s) {
        if (s.isBlank()) {
            throw new IllegalArgumentException(NOT_VALID_INPUT_BLANK.print());
        }
    }
}
