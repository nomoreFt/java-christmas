package christmas.v1.order.policy.rule.condition;

import christmas.v1.order.policy.rule.calculator.EventCalendar;
import christmas.v1.order.Order;
import christmas.v1.order.policy.rule.EventCondition;

public class SpecialDayEventCondition implements EventCondition {
    private EventCalendar eventCalendar;

    private SpecialDayEventCondition(EventCalendar eventCalendar) {
        this.eventCalendar = eventCalendar;
    }

    public static SpecialDayEventCondition create(EventCalendar eventCalendar) {
        return new SpecialDayEventCondition(eventCalendar);
    }

    @Override
    public boolean isSatisfiedBy(Order order) {
        return eventCalendar.isSpecialDay(order.getOrderedDate());
    }

}
