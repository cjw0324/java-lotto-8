package lotto.factory;

import lotto.domain.User;
import lotto.io.Input;
import lotto.io.Output;
import lotto.service.LottoSeller;

public class UserFactory {
    private final Input input;
    private final Output output;
    private final LottoSeller lottoSeller;

    public UserFactory(Input input, Output output, LottoSeller lottoSeller) {
        this.input = input;
        this.output = output;
        this.lottoSeller = lottoSeller;
    }

    public User createUser() {
        User user = new User();
        output.printAskPrice();

        while (true) {
            try {
                int price = input.readPrice();
                user.setPurchaseAmount(price);
                user.buy(lottoSeller);
                output.printBuyingTickets(user.getLottoList());
                return user;
            } catch (IllegalArgumentException e) {
                output.print(e.getMessage());
                output.printRetry();
            }
        }
    }
}
