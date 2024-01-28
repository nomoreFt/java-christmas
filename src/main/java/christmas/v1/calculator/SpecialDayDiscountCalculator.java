package christmas.v1.calculator;

import christmas.v1.Money;
import christmas.v1.order.Order;
import christmas.v1.rule.DiscountCalculator;

public class SpecialDayDiscountCalculator implements DiscountCalculator {
    private static final Money DISCOUNT_AMOUNT = Money.won(1000); // 할인 시작 금액
    @Override
    public Money calculateDiscount(Order order) {
        return DISCOUNT_AMOUNT;
    }
}
