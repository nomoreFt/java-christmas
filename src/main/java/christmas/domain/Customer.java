package christmas.domain;

import java.time.LocalDate;

public class Customer {
    private Badge badge;

    protected Customer(){};

    public void reserve(Restaurant restaurant, LocalDate reservationDate, ReservationMenu reservationMenu) {
        Reservation reservation = Reservation.of(reservationDate, reservationMenu);
        restaurant.reserve(reservation);
    }
}
