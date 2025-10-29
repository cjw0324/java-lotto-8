package lotto.service;

import static lotto.domain.policy.LottoPolicy.LOTTO_PRICE;
import static lotto.exception.ErrorMessage.NOT_VALID_MONEY;

import java.util.ArrayList;
import java.util.List;
import lotto.Lotto;

public class LottoSeller {
    private final LottoGenerator generator;

    public LottoSeller(LottoGenerator generator) {
        this.generator = generator;
    }

    public List<Lotto> selling(int money) {
        validatePurchaseAmount(money);
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < money / LOTTO_PRICE; i++) {
            lottos.add(generator.generate());
        }
        return lottos;
    }

    private void validatePurchaseAmount(int money) {
        if (money < LOTTO_PRICE || money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(NOT_VALID_MONEY.print());
        }
    }
}
