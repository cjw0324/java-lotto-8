package lotto.io;

import java.util.List;
import java.util.Map;
import lotto.Lotto;
import lotto.service.Rank;

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
        System.out.println("\n" + lottoList.size() + TICKETS_OUTPUT_PROMPT);
        for (Lotto lotto : lottoList) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void printAskWinningLotto() {
        System.out.println("\n" + WINNING_LOTTO_INPUT_PROMPT);
    }

    public void printAskBonus() {
        System.out.println("\n" + BONUS_INPUT_PROMPT);
    }

    public void printSummary(Map<Rank, Long> result, double yield) {
        System.out.println("\n" + SUMMARY_PROMPT);
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
        if (rank.hasBonus()) {
            printSecondRank(rank, count);
            return;
        }
        printNormalRank(rank, count);
    }

    private void printSecondRank(Rank rank, long count) {
        System.out.printf("%d개 일치, 보너스 볼 일치 (%s원) - %d개%n",
                rank.getMatchCount(),
                String.format("%,d", rank.getPrice()),
                count);
    }

    private void printNormalRank(Rank rank, long count) {
        System.out.printf("%d개 일치 (%s원) - %d개%n",
                rank.getMatchCount(),
                String.format("%,d", rank.getPrice()),
                count);
    }

    private void printYield(double yield) {
        System.out.printf("총 수익률은 %.1f%%입니다.%n", yield);
    }

    public void printRetry() {
        System.out.println(RETRY_PROMPT);
    }
}
