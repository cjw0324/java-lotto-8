package lotto.domain;

import static lotto.exception.Exception.NOT_VALID_LOTTO_CONTAIN_BONUS;

import java.util.List;
import lotto.Lotto;

public class WinningLotto{
    private final Lotto winningLotto;
    private final int bonus;
    public WinningLotto(Lotto lotto, int bonus) {
        this.winningLotto = lotto;
        this.bonus = bonus;
    }


    private void validate(Lotto winningNumbers, int bonus) {
        if (winningNumbers.getNumbers().contains(bonus)) {
            throw new IllegalArgumentException(NOT_VALID_LOTTO_CONTAIN_BONUS.print());
        }
    }

    public int countMatch(Lotto userLotto) {
        int match = 0;
        for (int userSelect : userLotto.getNumbers()) {
            if (winningLotto.getNumbers().contains(userSelect)) {
                match++;
            }
        }
        return match;
    }

    public boolean hasBonus(Lotto userLotto) {
        return userLotto.getNumbers().contains(bonus);
    }
}
