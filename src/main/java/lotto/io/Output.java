package lotto.io;

import java.util.List;
import lotto.Lotto;

public class Output {
    private static final String PRICE_INPUT_PROMPT = "구입금액을 입력해 주세요.";
    private static final String TICKETS_OUTPUT_PROMPT = "개를 구매했습니다.";
    private static final String WINNING_LOTTO_INPUT_PROMPT = "당첨 번호를 입력해 주세요.";
    private static final String BONUS_INPUT_PROMPT = "보너스 번호를 입력해 주세요.";
    private static final String SUMMARY_PROMPT = "당첨 통계\n---";
    private static final String RETRY_PROMPT = "다시 입력해주세요.";
    public void print(String s) {
        System.out.println(s);
    }

    public void printAskPrice() {
        System.out.println(PRICE_INPUT_PROMPT);
    }

    public void printBuyingTickets(List<Lotto> lottoList) {
        System.out.println(lottoList.size() + TICKETS_OUTPUT_PROMPT);
        for (Lotto lotto : lottoList) {
            System.out.println(lotto);
        }
    }

    public void printAskWinningLotto() {
        System.out.println(WINNING_LOTTO_INPUT_PROMPT);
    }

    public void printAskBonus() {
        System.out.println(BONUS_INPUT_PROMPT);
    }

    public void printSummary() {
        System.out.println(SUMMARY_PROMPT);
    }

    public void printRetry() {
        System.out.println(RETRY_PROMPT);
    }
}
