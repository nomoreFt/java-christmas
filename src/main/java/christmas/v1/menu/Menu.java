package christmas.v1.menu;

import christmas.v1.Money;

import javax.management.Query;

public class Menu {
    private MenuType menuType;
    private String name;
    private Money price;

    public boolean isSameType(MenuType menuType) {
        return this.menuType == menuType;
    }

    public Money getPrice() {
        return price;
    }
}
