package christmas.event;

import christmas.restaurant.OrderContext;

public interface EventCondition {
    boolean isSatisfied(OrderContext order);
}
