package christmas.domain;

import christmas.domain.event.common.Badge;

import java.time.LocalDate;
import java.util.Arrays;

public class Customer {
    private Badge badge;

    private Customer(){};
    private Customer(Badge badge) {
        this.badge = badge;
    }

    public static Customer of() {
        return new Customer();
    }

    public void awardBadge(Badge badge) {
        this.badge = badge;
    }
    public void reserve(Restaurant restaurant, int reservationDay, ReservationMenu... reservationMenus) {
        Reservation reservation = Reservation.of(reservationDay, reservationMenus);
        restaurant.reserve(reservation);
    }
}
