package christmas.v1.condition;

import christmas.v1.EventCalendar;
import christmas.v1.order.Order;
import christmas.v1.rule.EventCondition;

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
