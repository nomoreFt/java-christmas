package christmas.v0.event;

import christmas.v0.common.Money;
import christmas.v0.restaurant.OrderContext;

public interface DiscountEventPolicy {
    Money calculateDiscountAmount(OrderContext orderContext);
}
