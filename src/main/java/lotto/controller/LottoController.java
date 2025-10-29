package lotto.controller;

import java.util.List;
import java.util.Map;
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
        int purchaseAmount = input.readPrice();
        validate.availablePrice(purchaseAmount);

        User user = new User();
        user.setPurchaseAmount(purchaseAmount);
        user.buy(lottoSeller);

        List<Integer> winningNumbers = input.readLottoNums();
        for (int number : winningNumbers) {
            validate.availableLottoNum(number);
        }

        int bonusNumber = input.readPrice();
        validate.availableLottoNum(bonusNumber);

        // 5️⃣ 당첨 로또 생성
        WinningLotto winningLotto = new WinningLotto(new Lotto(winningNumbers), bonusNumber);

        // 6️⃣ 결과 계산
        LottoScoreCalc scoreCalc = new LottoScoreCalc(winningLotto);
        Map<Rank, Long> result = scoreCalc.getResult(user.getLottoList());

        // 7️⃣ 수익률 계산 및 출력
        int totalEarnings = (int) result.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();
        user.setTotalEarnings(totalEarnings);
        user.setYield();
    }

}
