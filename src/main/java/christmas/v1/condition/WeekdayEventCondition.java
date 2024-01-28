package christmas.v1.condition;

import christmas.v1.EventCalendar;
import christmas.v1.order.Order;
import christmas.v1.rule.BenefitDescription;
import christmas.v1.rule.EventCondition;

//평일 할인
public class WeekdayEventCondition implements EventCondition, BenefitDescription {
    private EventCalendar eventCalendar;
    @Override
    public boolean isSatisfiedBy(Order order) {
        // 주문일자가 크리스마스 D-day 이벤트 기간에 포함되는지 확인
        return eventCalendar.isWeekday(order.getOrderedDate());
    }

    @Override
    public String getDescription() {
        return "평일 할인";
    }
}
