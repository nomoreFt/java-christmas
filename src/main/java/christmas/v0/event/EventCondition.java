package christmas.v0.event;

import christmas.v0.restaurant.OrderContext;

public interface EventCondition {
    boolean isSatisfied(OrderContext order);
}
