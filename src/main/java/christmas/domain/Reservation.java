package christmas.domain;

import christmas.domain.event.common.Money;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reservation {
    private final int reservationDay;
    private final ReservationMenus reservationMenus;


    private Reservation(int reservationDay, ReservationMenus reservationMenus) {
        this.reservationDay = reservationDay;
        this.reservationMenus = reservationMenus;
    }

    public static Reservation of(int reservationDay, ReservationMenu... reservationMenus) {
        return new Reservation(reservationDay, ReservationMenus.of(reservationMenus));
    }

    public void validateReservation(EventCalendar eventCalendar, Menu menu) {
        if (!eventCalendar.isDecemberDay(reservationDay)) {
            throw new IllegalArgumentException("예약은 12월에만 가능합니다.");
        }

        if (!menu.isAvailable(reservationMenus)) {
            throw new IllegalArgumentException("예약 메뉴가 올바르지 않습니다.");
        }
    }

    public ReservationMenus getReservationMenus(){
        return this.reservationMenus;
    }
    public EventCalendar getReservationDay() {
        return EventCalendar.of(reservationDay);
    }
    public Money getTotalOrderAmount(Menu menu){
        return this.reservationMenus.calculateTotalOrderAmount(menu);
    }
}
