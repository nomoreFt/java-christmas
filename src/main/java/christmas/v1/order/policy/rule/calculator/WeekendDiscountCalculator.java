package christmas.v1.order.policy.rule.calculator;

import christmas.v1.common.Money;
import christmas.v1.menu.MenuType;
import christmas.v1.order.Order;
import christmas.v1.order.policy.rule.DiscountCalculator;

public class WeekendDiscountCalculator implements DiscountCalculator {
    private static final Money DISCOUNT_PER_MAIN = Money.won(2023);

    private WeekendDiscountCalculator() {
    }

    public static WeekendDiscountCalculator create() {
        return new WeekendDiscountCalculator();
    }
    @Override
    public Money calculateDiscount(Order order) {
        //메인메뉴 1개당 2,023원 할인
        //order의 Menu
        return DISCOUNT_PER_MAIN.multiply(order.countOrderItemBy(MenuType.MAIN));
    }
}
