package christmas.domain;

import christmas.domain.event.common.Money;
import christmas.domain.event.policy.EventContext;

public class Restaurant {
    Menu menu;
    EventCalendar eventCalendar;
    EventPlanner eventPlanner;

    public Restaurant(Menu menu, EventCalendar eventCalendar, EventPlanner eventPlanner) {
        this.menu = menu;
        this.eventCalendar = eventCalendar;
        this.eventPlanner = eventPlanner;
    }

    public ReservationConfirmation reserve(Reservation reservation) {
        //예약 가능 여부 확인
        reservation.validateReservation(eventCalendar, menu);

        //확인된 예약으로 이벤트 적용
        EventContext eventContext = EventContext.withOutDiscountMoney(reservation,menu);
        return eventPlanner.applyEvents(eventContext);
    }

}
