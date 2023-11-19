package christmas.domain.event.condition.discount;

import christmas.domain.EventCalendar;
import christmas.domain.event.common.Money;
import christmas.domain.event.condition.discount.DiscountEventCondition;
import christmas.domain.event.policy.EventContext;

public class HolidayDiscountCondition implements DiscountEventCondition {
    @Override
    public boolean isSatisfiedBy(EventContext context) {
        return context.isHoliday();
    }

    @Override
    public Money getDiscountMoney(EventContext context) {
        if (isSatisfiedBy(context)) {
            return EventContext.EVENT_YEAR_DISCOUNT.times(context.countAppetizer());
        }
        return Money.ZERO;
    }
}
