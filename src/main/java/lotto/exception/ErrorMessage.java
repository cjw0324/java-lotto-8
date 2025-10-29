package lotto.exception;

public enum ErrorMessage {
    NOT_VALID_LOTTO_MORE_THAN_SIX("로또 번호는 6개여야 합니다."),
    NOT_VALID_LOTTO_DUPLICATED("로또 번호에 중복이 존재합니다."),
    NOT_VALID_LOTTO_OUT_OF_RANGE("로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    NOT_VALID_LOTTO_CONTAIN_BONUS("보너스 번호는 당첨 번호와 중복될 수 없습니다."),
    NOT_VALID_MONEY("로또 구입 금액은 1,000원 단위여야 합니다."),
    NOT_VALID_INPUT_BLANK("입력에 공백은 허용되지 않습니다."),
    NOT_VALID_STRING("정수 입력만 가능합니다.");
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String print() {
        return "[ERROR] " + message;
    }
}
