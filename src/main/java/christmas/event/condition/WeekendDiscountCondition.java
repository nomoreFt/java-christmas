package christmas.event.condition;

import christmas.event.DecemberEventCalendar;
import christmas.event.EventCondition;
import christmas.restaurant.OrderContext;

public class WeekendDiscountCondition implements EventCondition {
    private DecemberEventCalendar DecemberEventCalendar;
    @Override
    public boolean isSatisfied(OrderContext order) {
        return DecemberEventCalendar.isWeekend(order.reserveDate());
    }
}
