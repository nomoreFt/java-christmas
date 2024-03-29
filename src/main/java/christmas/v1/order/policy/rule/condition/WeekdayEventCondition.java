package christmas.v1.order.policy.rule.condition;

import christmas.v1.order.policy.rule.calculator.EventCalendar;
import christmas.v1.order.Order;
import christmas.v1.order.policy.rule.EventCondition;

//평일 할인
public class WeekdayEventCondition implements EventCondition {
    private EventCalendar eventCalendar;

    private WeekdayEventCondition(EventCalendar eventCalendar) {
        this.eventCalendar = eventCalendar;
    }

    public static WeekdayEventCondition create(EventCalendar eventCalendar) {
        return new WeekdayEventCondition(eventCalendar);
    }

    @Override
    public boolean isSatisfiedBy(Order order) {
        // 주문일자가 평일인지 확인
        return eventCalendar.isWeekday(order.getOrderedDate());
    }

}
