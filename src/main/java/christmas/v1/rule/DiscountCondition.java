package christmas.v1.rule;

import christmas.v1.Order;

public interface DiscountCondition {
    boolean isSatisfiedBy(Order order);
}
