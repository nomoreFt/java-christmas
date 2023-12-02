package christmas.event.condition;

import christmas.event.DecemberEventCalendar;
import christmas.restaurant.OrderContext;

public class ChristmasDdayDiscountCondition implements EventCondition {
    private DecemberEventCalendar decemberEventCalendar;
    @Override
    public boolean isSatisfied(OrderContext order) {
        return decemberEventCalendar.isChristmasDday(order.reserveDate());
    }

}
