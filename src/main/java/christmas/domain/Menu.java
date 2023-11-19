package christmas.domain;

import christmas.domain.event.common.FoodType;
import christmas.domain.event.common.Money;

import java.util.List;

public class Menu {
    private List<MenuItem> menuItems;
    public boolean isAvailable(ReservationMenus reservationMenus) {
        return false;
    }

    public Money getPriceFromMenuName(String name) {
        return menuItems.stream()
                .filter(menuItem -> menuItem.getName().equals(name))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("메뉴가 존재하지 않습니다."))
                .getPrice();
    }

    public boolean isAppetizer(String name) {
        return menuItems.stream()
                .filter(menuItem -> menuItem.getName().equals(name))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("메뉴가 존재하지 않습니다."))
                .getFoodType().equals(FoodType.Appetizer);
    }

    public boolean isMainDish(String name) {
        return menuItems.stream()
                .filter(menuItem -> menuItem.getName().equals(name))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("메뉴가 존재하지 않습니다."))
                .getFoodType().equals(FoodType.Main);
    }
}
