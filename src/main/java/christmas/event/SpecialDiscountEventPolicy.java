package christmas.event;

import christmas.common.Money;
import christmas.order.EventExpectation;
import christmas.restaurant.OrderContext;

public class SpecialDiscountEventPolicy extends EventPolicy implements DiscountEventPolicy{
    public final Money specialDayDiscountAmount = Money.of(1_000);
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
