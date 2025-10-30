package lotto.io;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import lotto.exception.ErrorMessage;
import lotto.io.validate.LottoValidate;
import org.junit.jupiter.api.Test;

class InputTest extends NsTest {
    @Test
    void 구입금액_입력_정상() {
        // given
        Input input = new Input(new LottoValidate());

        // when
        run("8000");
        int price = input.readPrice();

        // then
        assertThat(price).isEqualTo(8000);
    }

    @Test
    void 구입금액이_1000원_단위가_아니면_예외발생() {
        // given
        Input input = new Input(new LottoValidate());

        //when
        run("850");

        //then
        assertThatThrownBy(input::readPrice)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith(ErrorMessage.NOT_VALID_MONEY.print());
    }

    @Test
    void 보너스번호_입력_정상() {
        // given
        Input input = new Input(new LottoValidate());

        // when
        run("7");
        int bonus = input.readBonus();

        // then
        assertThat(bonus).isEqualTo(7);
    }

    @Test
    void 보너스번호_입력_범위초과_예외발생() {
        // given
        Input input = new Input(new LottoValidate());

        // when
        run("46");

        // then
        assertThatThrownBy(input::readBonus)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NOT_VALID_LOTTO_OUT_OF_RANGE.print());
    }


    @Override
    protected void runMain() {

    }
}