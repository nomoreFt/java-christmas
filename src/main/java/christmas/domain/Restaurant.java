package christmas.domain;

import java.time.LocalDate;

public class Restaurant {
    EventPlanner eventPlanner;

    public Restaurant(EventPlanner eventPlanner) {
        this.eventPlanner = eventPlanner;
    }

    public ReservationConfirmation reserve(Reservation reservation) {
        return eventPlanner.checkEvent(reservation);
    }

    static class ReservationConfirmation {
        //주문메뉴
        private Reservation reservation;

        //할인 전 총 금액

        //증정 메뉴
        //혜택내역
        //총 혜택 금액
        //할인 후 예상 결제 금액
        //12월 이벤트 뱃지

        private Badge badge;

        public ReservationConfirmation(Reservation reservation, Badge badge) {
            this.reservation = reservation;
            this.badge = badge;
        }
    }
}
