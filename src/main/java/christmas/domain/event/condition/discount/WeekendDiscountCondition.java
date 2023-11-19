package christmas.domain.event.condition.discount;

import christmas.domain.event.common.Money;
import christmas.domain.event.policy.EventContext;

public class WeekendDiscountCondition implements DiscountEventCondition {
    @Override
    public boolean isSatisfiedBy(EventContext context) {
        return context.isWeekend();
    }

    @Override
    public Money getDiscountMoney(EventContext context) {
        if (isSatisfiedBy(context)) {
            return Money.wons((long) EventContext.EVENT_YEAR * context.countAppetizer());
        }
        return Money.ZERO;
    }
}
