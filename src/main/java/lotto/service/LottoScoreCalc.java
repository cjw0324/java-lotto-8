package lotto.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.Lotto;
import lotto.domain.WinningLotto;

public class LottoScoreCalc {
    private final WinningLotto winningLotto;

    public LottoScoreCalc(WinningLotto winningLotto) {
        this.winningLotto = winningLotto;
    }

    public Rank calculate(Lotto userLotto) {
        int matchCount = winningLotto.countMatch(userLotto);
        boolean hasBonus = winningLotto.hasBonus(userLotto);
        return Rank.getRank(matchCount, hasBonus);
    }

    public Map<Rank, Long> getResult(List<Lotto> lottos) {
        Map<Rank, Long> result = new HashMap<>();
        for (Lotto lotto : lottos) {
            Rank rank = calculate(lotto);
            result.put(rank, result.getOrDefault(rank, 0L) + 1);
        }
        return result;
    }
}
