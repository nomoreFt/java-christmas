package christmas.v1.rule;

import christmas.v1.Money;
import christmas.v1.Order;

public interface DiscountCalculator {
    Money calculateDiscount(Order order);
}
