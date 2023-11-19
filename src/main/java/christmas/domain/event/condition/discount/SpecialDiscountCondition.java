package christmas.domain.event.condition.discount;

import christmas.domain.EventCalendar;
import christmas.domain.event.common.Money;
import christmas.domain.event.condition.discount.DiscountEventCondition;
import christmas.domain.event.policy.EventContext;

public class SpecialDiscountCondition implements DiscountEventCondition {
    @Override
    public boolean isSatisfiedBy(EventContext context) {
        return context.isSpecialDay();
    }

    @Override
    public Money getDiscountMoney(EventContext context) {
        if(!isSatisfiedBy(context)){
            return EventContext.SPECIAL_DISCOUNT;
        }
        return Money.ZERO;
    }
}
