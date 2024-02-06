package christmas.v1;


public enum Badge {
    NONE("없음"),
    STAR("별"),
    TREE("트리"),
    SANTA("산타");

    private final String description;

    Badge(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

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

    //toString
    @Override
    public String toString() {
        return description;
    }
}
