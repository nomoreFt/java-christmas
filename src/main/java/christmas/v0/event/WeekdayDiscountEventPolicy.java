package christmas.v0.event;

import christmas.v0.common.Money;
import christmas.v0.order.EventExpectation;
import christmas.v0.restaurant.FoodType;
import christmas.v0.restaurant.OrderContext;

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
