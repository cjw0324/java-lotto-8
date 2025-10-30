package lotto.domain;

public enum Rank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NONE(0, false, 0);

    private final int matchCount;
    private final boolean containBonus;
    private final int price;

    Rank(int matchCount, boolean containBonus, int prize) {
        this.matchCount = matchCount;
        this.containBonus = containBonus;
        this.price = prize;
    }

    public static Rank getRank(int matchCount, boolean hasBonus) {
        if (matchCount == 6) return FIRST;
        if (matchCount == 5 && hasBonus) return SECOND;
        if (matchCount == 5) return THIRD;
        if (matchCount == 4) return FOURTH;
        if (matchCount == 3) return FIFTH;
        return NONE;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean hasBonus() {
        return containBonus;
    }

    public int getPrice() {
        return price;
    }
}
