package christmas.v0.event;

import christmas.v0.common.Money;
import christmas.v0.order.EventExpectation;
import christmas.v0.restaurant.OrderContext;

public class SpecialDiscountEventPolicy extends EventPolicy implements DiscountEventPolicy{
    public final Money specialDayDiscountAmount = Money.won(1_000);
    @Override
    protected void applyTo(OrderContext orderContext, EventExpectation eventExpectation) {
        eventExpectation.addAppliedEvent(this);
        eventExpectation.addDiscountAmount(calculateDiscountAmount(orderContext));
    }

    @Override
    public Money calculateDiscountAmount(OrderContext orderContext) {
        return specialDayDiscountAmount;
    }
}
