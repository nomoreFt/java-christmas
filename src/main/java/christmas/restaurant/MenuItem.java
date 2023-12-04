package christmas.restaurant;

import christmas.common.Money;

public class MenuItem {
    private String menuName;
    private Money price;
    private FoodType menuType;

    public MenuItem(String menuName, int price, FoodType menuType) {
        this.menuName = menuName;
        this.price = Money.won(price);
        this.menuType = menuType;
    }

    public boolean isSameFood(String menuName) {
        return this.menuName.equals(menuName);
    }
    public boolean isSameFoodType(FoodType foodType) {
        return this.menuType.equals(foodType);
    }

    public Money price() {
        return price;
    }
}
