package christmas.v0.event;

import christmas.v0.common.Money;
import christmas.v0.order.EventExpectation;
import christmas.v0.restaurant.FoodType;
import christmas.v0.restaurant.OrderContext;

public class WeekendDiscountEventPolicy extends EventPolicy implements DiscountEventPolicy{
    private final Money mainDiscountAmount = Money.won(2_023);
    @Override
    protected void applyTo(OrderContext orderContext, EventExpectation eventExpectation) {
        eventExpectation.addAppliedEvent(this);
        eventExpectation.addDiscountAmount(calculateDiscountAmount(orderContext));
    }

    @Override
    public Money calculateDiscountAmount(OrderContext orderContext) {
        return mainDiscountAmount.multiply(orderContext.getOrderedFoodTypeCnt(FoodType.MAIN));
    }
}
