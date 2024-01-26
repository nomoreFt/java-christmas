package christmas.v0.restaurant;

import christmas.v0.common.Money;

public record OrderContextItem(
    MenuItem menuItem,
    long count
) {
    public static OrderContextItem of(MenuItem menuItem, long count) {
        return new OrderContextItem(menuItem, count);
    }

    //menuItem의 가격과 count를 곱한 금액을 반환
    public Money calculateOriginTotalCost() {
        return menuItem.price().multiply(count);
    }

    public boolean isSameFoodType(FoodType foodType) {
        return menuItem.isSameFoodType(foodType);
    }
}
