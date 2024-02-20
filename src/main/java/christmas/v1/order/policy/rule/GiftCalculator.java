package christmas.v1.order.policy.rule;

import christmas.v1.order.Gift;
import christmas.v1.order.Order;

public interface GiftCalculator {
    Gift calculateGift(Order order);
}
