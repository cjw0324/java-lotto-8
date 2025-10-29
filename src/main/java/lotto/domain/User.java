package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.Lotto;

public class User {
    private final List<Lotto> lottoList;
    private int purchaseAmount;
    private int totalEarnings;
    private double yield;


    public User() {
        this.lottoList = new ArrayList<>();
    }

    public void addLotto(Lotto lotto) {
        this.lottoList.add(lotto);
    }

    public List<Lotto> getLottoList() {
        return new ArrayList<>(this.lottoList);
    }

    public void setPurchaseAmount(int amount) {
        this.purchaseAmount = amount;
    }

    public void setTotalEarnings(int earnings) {
        this.totalEarnings = earnings;
    }

    public int getPurchaseAmount() {
        return purchaseAmount;
    }

    public int getTotalEarnings() {
        return totalEarnings;
    }

    public void setYield() {
        this.yield = Math.round(((double) totalEarnings / (double) purchaseAmount) * 1000) / 10.0;
    }

    public double getYield() {
        return yield;
    }
}
