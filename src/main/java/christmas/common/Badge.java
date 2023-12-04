package christmas.common;

public enum Badge {
    NONE,STAR,TREE,SANTA;

    public static Badge calculateBadge(Money discountAmount) {
        if (discountAmount.isGreaterThanOrEqual(Money.won(20_000))) {
            return Badge.SANTA;
        }
        if (discountAmount.isGreaterThanOrEqual(Money.won(10_000))) {
            return Badge.TREE;
        }
        if (discountAmount.isGreaterThanOrEqual(Money.won(5_000))) {
            return Badge.STAR;
        }
        return Badge.NONE;
    }
}
