package lotto.domain.factory;

import lotto.domain.User;
import lotto.io.Input;
import lotto.io.Output;
import lotto.service.LottoSeller;
import lotto.util.RetryEmulator;

public class UserFactory {
    private final Input input;
    private final Output output;
    private final LottoSeller lottoSeller;
    private final RetryEmulator retryEmulator;

    public UserFactory(Input input, Output output, LottoSeller lottoSeller, RetryEmulator retryEmulator) {
        this.input = input;
        this.output = output;
        this.lottoSeller = lottoSeller;
        this.retryEmulator = retryEmulator;
    }

    public User createUser() {
        User user = new User();
        output.printAskPrice();

        return retryEmulator.retry( () -> {
            int price = input.readPrice();
            user.setPurchaseAmount(price);
            user.buy(lottoSeller);
            output.printBuyingTickets(user.getLottoList());
            return user;
        });
    }
}
