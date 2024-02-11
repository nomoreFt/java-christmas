package christmas.v1;

import christmas.v1.order.OrderItem;

import java.time.LocalDate;
import java.util.List;

public interface InputEventHandler {
    LocalDate getVisitDate();
    List<OrderItem> getOrderItems();
}
