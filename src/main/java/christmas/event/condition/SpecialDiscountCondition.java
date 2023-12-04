package christmas.event.condition;

import christmas.event.DecemberEventCalendar;
import christmas.event.EventCondition;
import christmas.restaurant.OrderContext;

public class SpecialDiscountCondition implements EventCondition {
    private DecemberEventCalendar decemberEventCalendar;
    @Override
    public boolean isSatisfied(OrderContext order) {
        return decemberEventCalendar.isSpecialDay(order.reserveDate());
    }
}
