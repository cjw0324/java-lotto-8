package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lotto.Lotto;
import lotto.service.LottoSeller;

public class User {
    private final List<Lotto> lottoList;
    private long purchaseAmount;
    private long totalEarnings;
    private double yield;


    public User() {
        this.lottoList = new ArrayList<>();
    }

    public void buy(LottoSeller seller) {
        lottoList.addAll(seller.selling(this.purchaseAmount));
    }

    public List<Lotto> getLottoList() {
        return new ArrayList<>(this.lottoList);
    }

    public void setPurchaseAmount(int amount) {
        this.purchaseAmount = amount;
    }


    public long getPurchaseAmount() {
        return purchaseAmount;
    }

    private void setYield() {
        this.yield = Math.round(((double) totalEarnings / (double) purchaseAmount) * 1000) / 10.0;
    }

    public double getYield() {
        return yield;
    }

    public void calculateEarnings(Map<Rank, Long> result) {
        this.totalEarnings = result.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();

        setYield();
    }
}
