package lotto;

import static lotto.exception.ErrorMessage.NOT_VALID_LOTTO_DUPLICATED;
import static lotto.exception.ErrorMessage.NOT_VALID_LOTTO_MORE_THAN_SIX;
import static lotto.exception.ErrorMessage.NOT_VALID_LOTTO_OUT_OF_RANGE;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(NOT_VALID_LOTTO_MORE_THAN_SIX.print());
        }

        if (hasDuplicate(numbers)) {
            throw new IllegalArgumentException(NOT_VALID_LOTTO_DUPLICATED.print());
        }

        if (!inRange(numbers)) {
            throw new IllegalArgumentException(NOT_VALID_LOTTO_OUT_OF_RANGE.print());
        }
    }

    private boolean hasDuplicate(List<Integer> numbers) {
        return numbers.size() != new HashSet<>(numbers).size();
    }

    private boolean inRange(List<Integer> numbers) {
        return numbers.stream().allMatch(num -> num >= 1 && num <= 45);
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}
