package christmas.v1;

import java.math.BigDecimal;

public class Money {
    //zero
    public static final Money ZERO = new Money(0);
    private final BigDecimal value;

    private Money(long value) {
        this.value = BigDecimal.valueOf(value);
    }

    private Money(BigDecimal value) {
        this.value = value;
    }

    public static Money won(long value) {
        return new Money(value);
    }

    public Money add(Money money) {
        return new Money(value.add(money.value));
    }

    public Money multiply(long value) {
        return new Money(this.value.multiply(BigDecimal.valueOf(value)));
    }

    public Money minus(Money money) {
        return new Money(value.subtract(money.value));
    }

    public boolean isGreaterThanOrEqual(Money other) {
        return value.compareTo(other.value) >= 0;
    }
}
