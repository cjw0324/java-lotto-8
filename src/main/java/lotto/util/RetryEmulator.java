package lotto.util;

import java.util.function.Supplier;
import lotto.io.Output;

public class RetryEmulator {
    private final Output output;

    public RetryEmulator(Output output) {
        this.output = output;
    }

    public <T> T retry(Supplier<T> supplier) {
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
