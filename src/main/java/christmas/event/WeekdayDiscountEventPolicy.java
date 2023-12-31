package christmas.event;

import christmas.common.Money;
import christmas.order.EventExpectation;
import christmas.restaurant.FoodType;
import christmas.restaurant.OrderContext;

public class WeekdayDiscountEventPolicy extends EventPolicy implements DiscountEventPolicy{

    private final Money dessertDiscountAmount = Money.won(2_023);
    @Override
    protected void applyTo(OrderContext orderContext, EventExpectation eventExpectation) {
        eventExpectation.addAppliedEvent(this);
        eventExpectation.addDiscountAmount(calculateDiscountAmount(orderContext));
    }

    public Money calculateDiscountAmount(OrderContext orderContext) {
        return dessertDiscountAmount.multiply(orderContext.getOrderedFoodTypeCnt(FoodType.DESSERT));
    }

}
