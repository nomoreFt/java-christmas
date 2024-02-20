package christmas.v1.order.policy.rule;

import christmas.v1.common.Money;
import christmas.v1.order.Order;

public interface DiscountCalculator {
    Money calculateDiscount(Order order);
}
