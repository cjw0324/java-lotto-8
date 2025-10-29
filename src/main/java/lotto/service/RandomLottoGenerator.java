package lotto.service;


import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import lotto.Lotto;
import lotto.domain.User;

public class RandomLottoGenerator implements LottoGenerator {
    @Override
    public Lotto generate() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return new Lotto(numbers);
    }
}
