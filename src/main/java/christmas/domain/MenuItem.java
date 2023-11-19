package christmas.domain;

import christmas.domain.event.common.FoodType;
import christmas.domain.event.common.Money;

public class MenuItem {
    private FoodType foodType;
    private String name;
    private Money price;

    private MenuItem(FoodType foodType, String name, Money price) {
        this.foodType = foodType;
        this.name = name;
        this.price = price;
    }

    public static MenuItem of(FoodType foodType, String name, Money price) {
        return new MenuItem(foodType, name, price);
    }

    public FoodType getFoodType() {
        return foodType;
    }
    public String getName(){
        return this.name;
    }

    public Money getPrice() {
        return price;
    }
}
