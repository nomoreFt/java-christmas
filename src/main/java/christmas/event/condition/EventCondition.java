package christmas.event.condition;

import christmas.restaurant.OrderContext;

public interface EventCondition {
    boolean isSatisfied(OrderContext order);
}
