package lotto.validate;

public class LottoValidate implements Validate{
    @Override
    public boolean availableLottoNum(int num) {
        return num >= 1 && num <= 45;
    }
}
