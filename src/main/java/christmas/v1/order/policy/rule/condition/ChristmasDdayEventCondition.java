package christmas.v1.order.policy.rule.condition;

import christmas.v1.order.policy.rule.calculator.EventCalendar;
import christmas.v1.order.Order;
import christmas.v1.order.policy.rule.EventCondition;

public class ChristmasDdayEventCondition implements EventCondition {

    //Rule에 합성하는걸 고민해봤는데 달력에 종속되지 않는 할인 정책을 위해 이렇게 구현했습니다.
    private EventCalendar eventCalendar;

    private ChristmasDdayEventCondition(EventCalendar eventCalendar) {
        this.eventCalendar = eventCalendar;
    }

    public static ChristmasDdayEventCondition create(EventCalendar eventCalendar) {
        return new ChristmasDdayEventCondition(eventCalendar);
    }

    @Override
    public boolean isSatisfiedBy(Order order) {
        // 주문일자가 크리스마스 D-day 이벤트 기간에 포함되는지 확인
        return eventCalendar.isBeforeChristmas(order.getOrderedDate());
    }

}
