package christmas.v0.event.condition;

import christmas.v0.event.DecemberEventCalendar;
import christmas.v0.event.EventCondition;
import christmas.v0.restaurant.OrderContext;

public class ChristmasDdayDiscountCondition implements EventCondition {
    private DecemberEventCalendar decemberEventCalendar;
    @Override
    public boolean isSatisfied(OrderContext order) {
        return decemberEventCalendar.isChristmasDday(order.reserveDate());
    }

}
