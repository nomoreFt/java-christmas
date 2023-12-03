package christmas.event;

import christmas.common.Money;
import christmas.order.EventExpectation;
import christmas.restaurant.FoodType;
import christmas.restaurant.OrderContext;

public class WeekendDiscountEventPolicy extends EventPolicy implements DiscountEventPolicy{
    private final Money mainDiscountAmount = Money.of(2_023);
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
