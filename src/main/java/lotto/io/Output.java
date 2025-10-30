package lotto.io;

import java.util.List;
import java.util.Map;
import lotto.Lotto;
import lotto.domain.Rank;

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
        print(PRICE_INPUT_PROMPT);
    }

    public void printBuyingTickets(List<Lotto> lottoList) {
        print("\n" + lottoList.size() + TICKETS_OUTPUT_PROMPT);
        for (Lotto lotto : lottoList) {
            print(lotto.getNumbers().toString());
        }
    }

    public void printAskWinningLotto() {
        print("\n" + WINNING_LOTTO_INPUT_PROMPT);
    }

    public void printAskBonus() {
        print("\n" + BONUS_INPUT_PROMPT);
    }

    public void printSummary(Map<Rank, Long> result, double yield) {
        print("\n" + SUMMARY_PROMPT);
        printRankStatistics(result);
        printYield(yield);
    }

    private void printRankStatistics(Map<Rank, Long> result) {
        for (Rank rank : Rank.values()) {
            if (rank.getMatchCount() < 3) {
                continue;
            }
            printRankLine(rank, result.getOrDefault(rank, 0L));
        }
    }

    private void printRankLine(Rank rank, long count) {
        String formattedPrice = String.format("%,d", rank.getPrice());
        String message;

        if (rank.hasBonus()) {
            message = String.format("%d개 일치, 보너스 볼 일치 (%s원) - %d개",
                    rank.getMatchCount(), formattedPrice, count);
        } else {
            message = String.format("%d개 일치 (%s원) - %d개",
                    rank.getMatchCount(), formattedPrice, count);
        }

        print(message);
    }

    private void printYield(double yield) {
        print(String.format("총 수익률은 %.1f%%입니다.", yield));
    }

    public void printRetry() {
        print(RETRY_PROMPT);
    }
}
