package christmas.domain.event.condition.discount;

import christmas.domain.EventCalendar;
import christmas.domain.Reservation;
import christmas.domain.event.common.Money;
import christmas.domain.event.policy.EventContext;

public interface DiscountEventCondition {
    boolean isSatisfiedBy(EventContext context);

    Money getDiscountMoney(EventContext context);
}
