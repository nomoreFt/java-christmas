package christmas.restaurant;

import christmas.common.Money;

public class MenuItem {
    private String menuName;
    private Money price;

    public MenuItem(String menuName, int price) {
        this.menuName = menuName;
        this.price = Money.of(price);
    }

    public boolean isSameFood(String menuName) {
        return this.menuName.equals(menuName);
    }

    public Money price() {
        return price;
    }
}
