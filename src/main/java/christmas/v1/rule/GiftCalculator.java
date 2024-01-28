package christmas.v1.rule;

import christmas.v1.Gift;
import christmas.v1.order.Order;

public interface GiftCalculator {
    Gift calculateGift(Order order);
}
