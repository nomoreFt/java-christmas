package christmas.v1.service;

import christmas.v1.order.EventPolicy;
import christmas.v1.order.EventValidator;
import christmas.v1.order.Order;
import christmas.v1.service.dto.OrderResult;

public class OrderApplyEventService {
    private final EventPolicy eventPolicy;
    private final EventValidator eventValidator;

    public OrderApplyEventService(EventPolicy eventPolicy, EventValidator eventValidator) {
        this.eventPolicy = eventPolicy;
        this.eventValidator = eventValidator;
    }

    public OrderResult requestOrder(Order order) {
        order.applyEvent(eventPolicy, eventValidator);
        return OrderResult.from(order);
    }
}
