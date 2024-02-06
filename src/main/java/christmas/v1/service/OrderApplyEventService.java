package christmas.v1.service;

import christmas.v1.EventPolicy;
import christmas.v1.order.Order;

public class OrderApplyEventService {
    private final EventPolicy eventPolicy;

    public OrderApplyEventService(EventPolicy eventPolicy) {
        this.eventPolicy = eventPolicy;
    }

    public OrderResult requestOrder(Order order) {
        order.applyEvent(eventPolicy);
        return OrderResult.from(order);
    }
}
