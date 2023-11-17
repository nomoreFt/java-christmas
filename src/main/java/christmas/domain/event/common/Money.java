package christmas.domain.event.common;

import java.math.BigDecimal;

public class Money {
    public static final Money ZERO = Money.wons(0);
    private BigDecimal amount;
    Money(BigDecimal amount) {
        this.amount = amount;
    }
    public static Money wons(long amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money wons(double amount) {
        return new Money(BigDecimal.valueOf(amount));
    }


    public Money plus(Money amount){
        return new Money(this.amount.add(amount.amount));
    }


}
