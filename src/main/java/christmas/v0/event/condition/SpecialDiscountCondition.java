package christmas.v0.event.condition;

import christmas.v0.event.DecemberEventCalendar;
import christmas.v0.event.EventCondition;
import christmas.v0.restaurant.OrderContext;

public class SpecialDiscountCondition implements EventCondition {
    private DecemberEventCalendar decemberEventCalendar;
    @Override
    public boolean isSatisfied(OrderContext order) {
        return decemberEventCalendar.isSpecialDay(order.reserveDate());
    }
}
