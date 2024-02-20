package christmas.v1.order;

import christmas.v1.order.Order;

public interface EventPolicy {
    void applyEvent(Order order);
}
