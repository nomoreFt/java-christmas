package christmas.v1.service;

import christmas.v1.order.Order;
import christmas.v1.order.OrderItem;

import java.time.LocalDate;
import java.util.List;

public class OrderResult {
    private final Order order;

    public OrderResult(Order order) {
        this.order = order;
    }

    public LocalDate getOrderedDate() {
        return order.getOrderedDate();
    }

    public List<OrderItem> getOrderList() {
        return order.getOrderItems();
    }
}
