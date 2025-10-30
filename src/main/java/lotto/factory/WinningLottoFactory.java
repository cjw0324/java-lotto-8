package lotto.factory;

import java.util.function.Supplier;
import lotto.Lotto;
import lotto.domain.WinningLotto;
import lotto.io.Input;
import lotto.io.Output;
import lotto.util.RetryEmulator;

public class WinningLottoFactory {
    private final Input input;
    private final Output output;
    private final RetryEmulator retryEmulator;

    public WinningLottoFactory(Input input, Output output, RetryEmulator retryEmulator) {
        this.input = input;
        this.output = output;
        this.retryEmulator = retryEmulator;
    }

    public WinningLotto createWinningLotto() {
        output.printAskWinningLotto();
        Lotto winningLotto = retryEmulator.retry(input::readLottoNums);
        output.printAskBonus();
        return retryEmulator.retry(() -> new WinningLotto(winningLotto, input.readBonus()));
    }



}
