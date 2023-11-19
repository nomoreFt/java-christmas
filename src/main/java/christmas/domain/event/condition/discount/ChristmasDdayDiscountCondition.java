package christmas.domain.event.condition.discount;

import christmas.domain.EventCalendar;
import christmas.domain.Reservation;
import christmas.domain.event.common.Money;
import christmas.domain.event.policy.EventContext;

public class ChristmasDdayDiscountCondition implements DiscountEventCondition {
    @Override
    public boolean isSatisfiedBy(EventContext context) {
        return context.isBeforeOrEaqualChristMas();
    }

    @Override
    public Money getDiscountMoney(EventContext context) {
        if(isSatisfiedBy(context)){
            int day = context.getReservationDay();
            Money extraDiscountAmount = EventContext.CHRISTMAS_D_DAY_DISCOUNT_DAY.times(day - 1);
            return EventContext.CHRISTMAS_D_DAY_DISCOUNT_DEFAULT.plus(extraDiscountAmount);
        }
        return Money.ZERO;
    }
}
