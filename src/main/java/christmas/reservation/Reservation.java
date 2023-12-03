package christmas.reservation;

import christmas.common.Badge;
import christmas.order.Customer;
import christmas.order.EventExpectation;
import christmas.order.Order;

public record Reservation(
        Order order,
        Customer customer,
        EventExpectation eventExpectation,
        Badge badge
) {
    public static Reservation create(Order order, Customer customer, EventExpectation eventExpectation) {
        return new Reservation(order, customer, eventExpectation, eventExpectation.getBadge());
    }
}
