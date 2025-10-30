package lotto.config;

import lotto.controller.LottoController;
import lotto.domain.WinningLotto;
import lotto.factory.UserFactory;
import lotto.factory.WinningLottoFactory;
import lotto.io.Input;
import lotto.io.Output;
import lotto.service.LottoGenerator;
import lotto.service.LottoSeller;
import lotto.service.RandomLottoGenerator;
import lotto.util.RetryEmulator;
import lotto.validate.LottoValidate;
import lotto.validate.Validate;

public class ComponentManager {
    private static final ComponentManager COMPONENT_MANAGER = new ComponentManager();
    private final LottoController lottoController;

    private ComponentManager() {
        LottoGenerator lottoGenerator = new RandomLottoGenerator();
        Validate validate = new LottoValidate();

        LottoSeller lottoSeller = new LottoSeller(lottoGenerator);
        Input input = new Input(validate);
        Output output = new Output();

        RetryEmulator retryEmulator = new RetryEmulator(output);
        UserFactory userFactory = new UserFactory(input, output, lottoSeller, retryEmulator);
        WinningLottoFactory winningLottoFactory = new WinningLottoFactory(input, output, retryEmulator);

        lottoController = new LottoController(
                winningLottoFactory, userFactory, output
        );
    }

    public static ComponentManager getInstance() {
        return COMPONENT_MANAGER;
    }

    public void run() {
        lottoController.run();
    }
}
