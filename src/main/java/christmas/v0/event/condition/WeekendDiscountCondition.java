package christmas.v0.event.condition;

import christmas.v0.event.EventCondition;
import christmas.v0.restaurant.OrderContext;

public class WeekendDiscountCondition implements EventCondition {
    private christmas.v0.event.DecemberEventCalendar DecemberEventCalendar;
    @Override
    public boolean isSatisfied(OrderContext order) {
        return DecemberEventCalendar.isWeekend(order.reserveDate());
    }
}
