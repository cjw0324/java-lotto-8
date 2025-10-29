package lotto.io;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import lotto.io.parser.InputParser;

public class Input {

    private final InputParser inputParser;

    public Input() {
        this.inputParser = new InputParser();
    }

    public int readPrice() {
        String rawPrice = Console.readLine();
        return inputParser.parseNum(rawPrice);
    }

    public List<Integer> readLottoNums() {
        String rawNums = Console.readLine();
        return inputParser.parseNums(rawNums);
    }


}
