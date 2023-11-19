package christmas.domain;

import christmas.domain.event.common.Money;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReservationMenus {
    List<ReservationMenu> reservationMenus = new ArrayList<>();

    private ReservationMenus(List<ReservationMenu> reservationMenus) {
        this.reservationMenus = Collections.unmodifiableList(reservationMenus);
    }

    public static ReservationMenus of(ReservationMenu... reservationMenus) {
        return new ReservationMenus(Arrays.asList(reservationMenus));
    }

    public Money calculateTotalOrderAmount(Menu menu) {
        return reservationMenus.stream()
                .map(reservationMenu -> menu.getPriceFromMenuName(reservationMenu.getName())
                        .times(reservationMenu.getOrderCount()))
                .reduce(Money.ZERO, Money::plus);
    }

    public int countAppetizer(Menu menu) {
        int count = 0;
        for(ReservationMenu reservationMenu : reservationMenus) {
            if(menu.isAppetizer(reservationMenu.getName())) {
                count += reservationMenu.getOrderCount();
            }
        }

        return count;
    }

    public int countMainDish(Menu menu) {
        int count = 0;
        for(ReservationMenu reservationMenu : reservationMenus) {
            if(menu.isMainDish(reservationMenu.getName())) {
                count += reservationMenu.getOrderCount();
            }
        }

        return count;
    }
}
