package christmas.domain;

import java.time.LocalDate;

public class Restaurant {
    Menu menu;
    EventPlanner eventPlanner;
    public void reserve(LocalDate reservationDate, ReservationMenu reservationMenu) {
        //reservation 객체 스스로 검증
        Reservation reservation = new Reservation(reservationDate, reservationMenu);
        eventPlanner.checkEvent(reservation);
    }


}
