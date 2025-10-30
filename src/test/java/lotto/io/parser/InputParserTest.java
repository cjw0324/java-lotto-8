package lotto.io.parser;

import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputParserTest {
    @Test
    void 숫자가_아닌_입력을_숫자로_변환할때_예외가_발생한다() {
        assertThatThrownBy(() -> new InputParser().parseNum("a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NOT_VALID_STRING.print())
        ;
    }

    @Test
    void 공백_문자열인_경우_예외가_발생한다() {
        assertThatThrownBy(() -> new InputParser().parseNum("  "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NOT_VALID_INPUT_BLANK.print());
    }

    @Test
    void 숫자로_변환될_수_없는_문자열이_포함되면_예외가_발생한다() {
        assertThatThrownBy(() -> new InputParser().parseNums("1,2,3,46,31,a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NOT_VALID_STRING.print());
    }

    @Test
    void 숫자로_변환될_수_없는_공백이_포함되면_예외가_발생한다() {
        assertThatThrownBy(() -> new InputParser().parseNums("1,2,3,46, ,5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NOT_VALID_INPUT_BLANK.print());
    }
}