package lotto.service;

import java.util.ArrayList;
import java.util.List;
import lotto.Lotto;

public class LottoSeller {
    static final int LOTTO_PRICE = 1_000;
    private final LottoGenerator generator;

    public LottoSeller(LottoGenerator generator) {
        this.generator = generator;
    }

    public List<Lotto> selling(int totalMoney) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < totalMoney / LOTTO_PRICE; i++) {
            lottos.add(generator.generate());
        }
        return lottos;
    }
}
