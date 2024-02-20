package christmas.v1.order.policy.rule.condition;

import christmas.v1.order.policy.rule.calculator.EventCalendar;
import christmas.v1.order.Order;
import christmas.v1.order.policy.rule.EventCondition;

public class WeekendEventCondition implements EventCondition{
    private EventCalendar eventCalendar;

    private WeekendEventCondition(EventCalendar eventCalendar) {
        this.eventCalendar = eventCalendar;
    }

    public static WeekendEventCondition create(EventCalendar eventCalendar) {
        return new WeekendEventCondition(eventCalendar);
    }

    @Override
    public boolean isSatisfiedBy(Order order) {
        return eventCalendar.isWeekend(order.getOrderedDate());
    }


}

