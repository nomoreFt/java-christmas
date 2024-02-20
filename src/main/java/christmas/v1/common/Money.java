package christmas.v1.common;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

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

    public String toString() {
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.getDefault());
        return formatter.format(value);
    }

    public boolean isGreaterThan(Money zero) {
        return value.compareTo(zero.value) > 0;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money money)) return false;
        return Objects.equals(value, money.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
