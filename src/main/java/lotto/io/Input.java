package lotto.io;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import lotto.Lotto;
import lotto.io.parser.InputParser;
import lotto.validate.Validate;

public class Input {

    private final InputParser inputParser;
    private final Validate validate;

    public Input(Validate validate) {
        this.inputParser = new InputParser();
        this.validate = validate;
    }

    public int readPrice() {
        String rawPrice = Console.readLine();
        int price = inputParser.parseNum(rawPrice);
        return validate.availablePrice(price);
    }

    public Lotto readLottoNums() {
        String rawNums = Console.readLine();
        List<Integer> lotto = inputParser.parseNums(rawNums);
        for (int num : lotto) {
            validate.availableLottoNum(num);
        }
        return new Lotto(lotto);
    }

    public int readBonus() {
        String rawBonus = Console.readLine();
        int bonus = inputParser.parseNum(rawBonus);
        return validate.availableLottoNum(bonus);
    }


}
