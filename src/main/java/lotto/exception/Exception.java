package lotto.exception;

public enum Exception {
    NOT_VALID_LOTTO_MORE_THAN_SIX("로또 번호는 6개여야 합니다."),
    NOT_VALID_LOTTO_DUPLICATED("로또 번호에 중복이 존재합니다."),
    NOT_VALID_LOTTO_OUT_OF_RANGE("로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    NOT_VALID_LOTTO_CONTAIN_BONUS("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    private final String message;

    Exception(String message) {
        this.message = message;
    }

    public String print() {
        return "[ERROR] " + message;
    }
}
