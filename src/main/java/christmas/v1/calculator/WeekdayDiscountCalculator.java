package christmas.v1.calculator;

import christmas.v1.Money;
import christmas.v1.menu.MenuType;
import christmas.v1.order.Order;
import christmas.v1.rule.DiscountCalculator;

public class WeekdayDiscountCalculator implements DiscountCalculator {
    private static final Money DISCOUNT_PER_DESSERT = Money.won(2023);
    @Override
    public Money calculateDiscount(Order order) {
        //디저트메뉴 1개당 2,023원 할인
        //order의 Menu
        return DISCOUNT_PER_DESSERT.multiply(order.countOrderItemBy(MenuType.DESSERT));
    }
}
