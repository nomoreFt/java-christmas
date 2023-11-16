package christmas.domain;

import java.time.LocalDate;

public class Reservation {
    private LocalDate reservationDate;
    private ReservationMenu reservationMenu;

    private Reservation(LocalDate reservationDate, ReservationMenu reservationMenu) {
        this.reservationDate = reservationDate;
        this.reservationMenu = reservationMenu;
    }

    public static Reservation of(LocalDate reservationDate, ReservationMenu reservationMenu) {
        return new Reservation(reservationDate, reservationMenu);
    }
}
