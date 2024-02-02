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

    public boolean isSameName(String menuName) {
        return this.name.equals(menuName);
    }

    public String getName() {
        return name;
    }
}
