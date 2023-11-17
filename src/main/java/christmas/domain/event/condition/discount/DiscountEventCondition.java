package christmas.domain.event.condition.discount;

import christmas.domain.Reservation;
import christmas.domain.event.common.Money;

public interface DiscountEventCondition {
    boolean isSatisfiedBy(Reservation reservation);
    Money getDiscountMoney(Reservation reservation);
}
