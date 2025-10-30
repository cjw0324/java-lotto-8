package lotto.io.validate;


import static lotto.domain.constant.LottoConstant.LOTTO_MAX_NUMBER;
import static lotto.domain.constant.LottoConstant.LOTTO_MIN_NUMBER;
import static lotto.domain.constant.LottoConstant.LOTTO_PRICE;
import static lotto.exception.ErrorMessage.NOT_VALID_LOTTO_OUT_OF_RANGE;
import static lotto.exception.ErrorMessage.NOT_VALID_MONEY;

public class LottoValidate implements Validate {
    @Override
    public int availableLottoNum(int num) {
        if (num < LOTTO_MIN_NUMBER || num > LOTTO_MAX_NUMBER) {
            throw new IllegalArgumentException(NOT_VALID_LOTTO_OUT_OF_RANGE.print());
        }
        return num;
    }

    @Override
    public int availablePrice(int price) {
        if (price < LOTTO_PRICE || price % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(NOT_VALID_MONEY.print());
        }
        return price;
    }
}
