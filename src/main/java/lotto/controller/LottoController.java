package lotto.controller;

import java.util.Map;
import lotto.domain.User;
import lotto.domain.WinningLotto;
import lotto.domain.factory.UserFactory;
import lotto.domain.factory.WinningLottoFactory;
import lotto.io.Output;
import lotto.service.LottoScoreCalc;
import lotto.domain.Rank;

public class LottoController {
    private final WinningLottoFactory winningLottoFactory;
    private final UserFactory userFactory;
    private final Output output;

    public LottoController(WinningLottoFactory winningLottoFactory, UserFactory userFactory, Output output) {
        this.winningLottoFactory = winningLottoFactory;
        this.userFactory = userFactory;
        this.output = output;
    }

    public void run() {
        User user = userFactory.createUser();
        WinningLotto winningLotto = winningLottoFactory.createWinningLotto();

        LottoScoreCalc scoreCalc = new LottoScoreCalc(winningLotto);
        Map<Rank, Long> result = scoreCalc.getResult(user.getLottoList());

        user.calculateEarnings(result);
        output.printSummary(result, user.getYield());
    }
}
