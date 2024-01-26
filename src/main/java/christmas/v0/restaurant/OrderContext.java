package christmas.v0.restaurant;

import christmas.v0.common.Money;

import java.time.LocalDate;
import java.util.List;

public record OrderContext(LocalDate reserveDate, List<OrderContextItem> orderFoodItems) {
    public static OrderContext of(LocalDate reserveDate, List<OrderContextItem> orderFoodItems) {
        return new OrderContext(reserveDate, orderFoodItems);
    }

    //orderFoodItems에서 총 음식 비용 추출
    public Money getTotalFoodCost() {
        return orderFoodItems.stream()
            .map(OrderContextItem::calculateOriginTotalCost)
            .reduce(Money.ZERO, Money::add);
    }

    public long getOrderedFoodTypeCnt(FoodType foodType) {
        return orderFoodItems.stream()
            .filter(orderContextItem -> orderContextItem.isSameFoodType(foodType))
            .count();
    }
}
