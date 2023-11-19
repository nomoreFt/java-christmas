package christmas.domain.event.policy;

import christmas.domain.*;
import christmas.domain.event.common.Money;
public class EventContext {
    public static final int EVENT_YEAR = 2023;
    public static final Money EVENT_YEAR_DISCOUNT = Money.wons(2023);
    public static final Money SPECIAL_DISCOUNT = Money.wons(1000);
    public static final Money CHRISTMAS_D_DAY_DISCOUNT_DAY = Money.wons(100);
    public static final Money CHRISTMAS_D_DAY_DISCOUNT_DEFAULT = Money.wons(1000);
    private Reservation reservation;
    private Money totalDiscountAmount;
    private Menu menu;


    private EventContext(Reservation reservation, Money totalDiscountAmount, Menu menu) {
        this.reservation = reservation;
        this.totalDiscountAmount = totalDiscountAmount;
        this.menu = menu;
    }

    public static EventContext withOutDiscountMoney(Reservation reservation, Menu menu) {
        return new EventContext(reservation, null, menu);
    }

    public Reservation getReservation() {
        return reservation;
    }
    public Money getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    public void setTotalDiscountAmount(Money money) {this.totalDiscountAmount = money;}

    public boolean isCalculatedDiscount() {
        return totalDiscountAmount != null;
    }

    public Money calculateTotalOrderAmount() {
        Money totalOrderAmount = Money.ZERO;
        return reservation.getReservationMenus().calculateTotalOrderAmount(menu);
    }

    public int countAppetizer() {
        return reservation.getReservationMenus().countAppetizer(menu);
    }

    public int countMainDish() {
        return reservation.getReservationMenus().countMainDish(menu);
    }

    public boolean isWeekend() {
        return reservation.getReservationDay().isWeekend(EVENT_YEAR);
    }

    public boolean isHoliday() {
        return reservation.getReservationDay().isHoliday(EVENT_YEAR);
    }

    public boolean isSpecialDay(){
        return reservation.getReservationDay().hasStar(EVENT_YEAR);
    }

    public boolean isBeforeOrEaqualChristMas(){
        return reservation.getReservationDay().isBeforeOrEaqualChristMas();
    }

    public int getReservationDay(){
        return reservation.getReservationDay().getDay();
    }
}
