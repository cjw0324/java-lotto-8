package lotto.domain.factory;

import static org.junit.jupiter.api.Assertions.*;

import camp.nextstep.edu.missionutils.test.NsTest;
import lotto.domain.User;
import lotto.io.Input;
import lotto.io.Output;
import lotto.io.validate.LottoValidate;
import lotto.service.LottoSeller;
import lotto.service.RandomLottoGenerator;
import lotto.util.RetryEmulator;
import org.junit.jupiter.api.Test;

class UserFactoryTest extends NsTest {
    @Test
    void 유저_팩토리_유저_객체_생성_정상입력() {
        UserFactory userFactory = new UserFactory(new Input(new LottoValidate()), new Output(),
                new LottoSeller(new RandomLottoGenerator()), new RetryEmulator(new Output()));

        run("14000");
        User user = userFactory.createUser();
        assertEquals(14000, user.getPurchaseAmount());
        assertEquals(14, user.getLottoList().size());
    }

    @Test
    void 유저_팩토리_유저_객체_생성_재시도() {
        UserFactory userFactory = new UserFactory(new Input(new LottoValidate()), new Output(),
                new LottoSeller(new RandomLottoGenerator()), new RetryEmulator(new Output()));

        run("14500", "10000");
        User user = userFactory.createUser();
        assertEquals(10000, user.getPurchaseAmount());
        assertEquals(10, user.getLottoList().size());
    }

    @Override
    protected void runMain() {

    }
}