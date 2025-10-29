package lotto.config;

import lotto.controller.LottoController;

public class ComponentManager {
    private static final ComponentManager COMPONENT_MANAGER = new ComponentManager();
    private final LottoController lottoController;

    private ComponentManager() {
        lottoController = new LottoController();
    }

    public static ComponentManager getInstance() {
        return COMPONENT_MANAGER;
    }

    public void run() {
        lottoController.run();
    }
}
