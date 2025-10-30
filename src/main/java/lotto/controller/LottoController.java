package lotto.controller;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import lotto.Lotto;
import lotto.domain.User;
import lotto.domain.WinningLotto;
import lotto.factory.UserFactory;
import lotto.factory.WinningLottoFactory;
import lotto.io.Input;
import lotto.io.Output;
import lotto.service.LottoScoreCalc;
import lotto.service.LottoSeller;
import lotto.service.Rank;
import lotto.validate.Validate;

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

        output.printAskWinningLotto();
        Lotto lotto = retryUntilValid(input::readLottoNums);

        output.printAskBonus();
        WinningLotto winningLotto = retryUntilValid(() -> {
            return new WinningLotto(lotto, input.readBonus());
        });

        LottoScoreCalc scoreCalc = new LottoScoreCalc(winningLotto);
        Map<Rank, Long> result = scoreCalc.getResult(user.getLottoList());

        long totalEarnings = result.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
        user.setTotalEarnings(totalEarnings);
        user.setYield();
        output.printSummary(result, user.getYield());
    }


    private <T> T retryUntilValid(Supplier<T> inputSupplier) {
        while (true) {
            try {
                return inputSupplier.get();
            } catch (IllegalArgumentException e) {
                output.print(e.getMessage());
                output.printRetry();
            }
        }
    }

}
