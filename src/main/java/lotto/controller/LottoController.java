package lotto.controller;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import lotto.Lotto;
import lotto.domain.User;
import lotto.domain.WinningLotto;
import lotto.io.Input;
import lotto.io.Output;
import lotto.service.LottoScoreCalc;
import lotto.service.LottoSeller;
import lotto.service.Rank;
import lotto.validate.Validate;

public class LottoController {
    private final LottoSeller lottoSeller;
    private final Validate validate;
    private final Input input;
    private final Output output;

    public LottoController(LottoSeller lottoSeller, Validate validate, Input input, Output output) {
        this.lottoSeller = lottoSeller;
        this.validate = validate;
        this.input = input;
        this.output = output;
    }

    public void run() {
        User user = new User();
        output.printAskPrice();
        user.setPurchaseAmount(retryUntilValid(input::readPrice));
        user.buy(lottoSeller);

        output.printAskWinningLotto();
        Lotto lotto = retryUntilValid(input::readLottoNums);

        output.printAskBonus();
        WinningLotto winningLotto = retryUntilValid(() -> {
            return new WinningLotto(lotto, input.readBonus());
        });

        LottoScoreCalc scoreCalc = new LottoScoreCalc(winningLotto);
        Map<Rank, Long> result = scoreCalc.getResult(user.getLottoList());

        int totalEarnings = (int) result.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();
        user.setTotalEarnings(totalEarnings);
        user.setYield();
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
