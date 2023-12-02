package christmas.order;

import christmas.reservation.Reservation;
import christmas.restaurant.MenuItem;
import christmas.restaurant.Restaurant;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private Restaurant restaurant;
    private LocalDate reserveDate;
    private List<OrderItem> items;

    public Order(LocalDate reserveDate, List<OrderItem> items) {
        this.reserveDate = reserveDate;
        this.items = items;
    }

    public Reservation reserve(Customer customer) {
        return new Reservation(this, customer, calculateEvent(reserveDate,items));
    }

    private EventExpectation calculateEvent(LocalDate reserveDate, List<OrderItem> items) {
        return restaurant.calculateApplyRestaurantEvent(reserveDate, items);
    }


}
