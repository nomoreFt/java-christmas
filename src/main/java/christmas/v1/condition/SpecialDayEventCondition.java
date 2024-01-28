package christmas.v1.condition;

import christmas.v1.EventCalendar;
import christmas.v1.order.Order;
import christmas.v1.rule.BenefitDescription;
import christmas.v1.rule.EventCondition;

public class SpecialDayEventCondition implements EventCondition, BenefitDescription {
    private EventCalendar eventCalendar;
    @Override
    public boolean isSatisfiedBy(Order order) {
        return eventCalendar.isSpecialDay(order.getOrderedDate());
    }

    @Override
    public String getDescription() {
        return "특별 할인";
    }
}
