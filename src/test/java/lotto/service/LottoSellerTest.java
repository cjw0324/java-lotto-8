package lotto.service;

import static lotto.domain.constant.LottoConstant.LOTTO_PRICE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import lotto.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoSellerTest {

    static final List<Integer> TEST_LOTTO = List.of(1, 2, 3, 4, 5, 6);

    // 내부용 Fake Generator
    static class FakeLottoGenerator implements LottoGenerator {
        @Override
        public Lotto generate() {
            return new Lotto(TEST_LOTTO);
        }
    }

    @Test
    void 판매_정상_금액() {
        // given
        LottoSeller seller = new LottoSeller(new FakeLottoGenerator());

        // when
        List<Lotto> result = seller.selling(LOTTO_PRICE * 3);

        // then
        assertThat(result).hasSize(3);
        for (Lotto lotto : result) {
            assertThat(lotto).isInstanceOf(Lotto.class);
            assertEquals(TEST_LOTTO, lotto.getNumbers());
        }
    }

    @Test
    @DisplayName("1000원 미만 입력 시 예외 발생")
    void 금액이_1000원_미만_예외() {
        // given
        LottoSeller seller = new LottoSeller(new FakeLottoGenerator());

        // when & then
        assertThatThrownBy(() -> seller.selling(500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]")
                .hasMessageContaining("1,000원 단위");
    }

    @Test
    @DisplayName("1000원 단위가 아닐 경우 예외 발생")
    void 금액이_1000원단위_아님_예외() {
        // given
        LottoSeller seller = new LottoSeller(new FakeLottoGenerator());

        // when & then
        assertThatThrownBy(() -> seller.selling(2500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]")
                .hasMessageContaining("1,000원 단위");
    }
}