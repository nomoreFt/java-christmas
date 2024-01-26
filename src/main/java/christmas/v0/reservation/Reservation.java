package christmas.v0.reservation;

import christmas.v0.common.Badge;
import christmas.v0.order.Customer;
import christmas.v0.order.EventExpectation;
import christmas.v0.order.Order;

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
