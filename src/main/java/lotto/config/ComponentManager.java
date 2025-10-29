package lotto.config;

import lotto.controller.LottoController;
import lotto.io.Input;
import lotto.io.Output;
import lotto.service.LottoGenerator;
import lotto.service.LottoSeller;
import lotto.service.RandomLottoGenerator;
import lotto.validate.LottoValidate;
import lotto.validate.Validate;

public class ComponentManager {
    private static final ComponentManager COMPONENT_MANAGER = new ComponentManager();
    private final LottoController lottoController;

    private ComponentManager() {
        LottoGenerator lottoGenerator = new RandomLottoGenerator();
        Validate validate = new LottoValidate();
        LottoSeller lottoSeller = new LottoSeller(lottoGenerator);
        Input input = new Input();
        Output output = new Output();

        lottoController = new LottoController(
                lottoSeller,
                validate,
                input,
                output
                );
    }

    public static ComponentManager getInstance() {
        return COMPONENT_MANAGER;
    }

    public void run() {
        lottoController.run();
    }
}
