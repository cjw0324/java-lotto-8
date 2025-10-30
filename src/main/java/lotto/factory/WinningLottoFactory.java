package lotto.factory;

import java.util.function.Supplier;
import lotto.Lotto;
import lotto.domain.WinningLotto;
import lotto.io.Input;
import lotto.io.Output;

public class WinningLottoFactory {
    private final Input input;
    private final Output output;

    public WinningLottoFactory(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    public WinningLotto createWinningLotto() {
        output.printAskWinningLotto();
        Lotto winningLotto = retryUntilValid(() -> input.readLottoNums());

        output.printAskBonus();
        return retryUntilValid(() -> new WinningLotto(winningLotto, input.readBonus()));
    }

    private <T> T retryUntilValid(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                output.print(e.getMessage());
                output.printRetry();
            }
        }
    }

}
