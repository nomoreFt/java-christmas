package christmas.event;

import christmas.common.Money;
import christmas.order.EventExpectation;
import christmas.restaurant.FoodType;
import christmas.restaurant.OrderContext;

public interface DiscountEventPolicy {
    Money calculateDiscountAmount(OrderContext orderContext);
}
