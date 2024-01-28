package christmas.v1.rule;

import christmas.v1.order.Order;

public interface EventCondition {
    boolean isSatisfiedBy(Order order);
}
