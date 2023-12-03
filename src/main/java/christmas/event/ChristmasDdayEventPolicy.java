package christmas.event;

import christmas.common.Money;
import christmas.order.EventExpectation;
import christmas.restaurant.OrderContext;

import java.time.LocalDate;

public class ChristmasDdayEventPolicy extends EventPolicy implements DiscountEventPolicy{
    private final Money discountStartPrice = Money.of(1_000);
    private final Money discountAddPrice = Money.of(100);
    private DecemberEventCalendar eventCalendar;
    @Override
    protected void applyTo(OrderContext orderContext, EventExpectation eventExpectation) {
        eventExpectation.addAppliedEvent(this);
        eventExpectation.addDiscountAmount(calculateDiscountAmount(orderContext));
    }

    public Money calculateDiscountAmount(OrderContext orderContext) {
        long d_dayCount = eventCalendar.daysUntilChristmas(orderContext.reserveDate());
        return discountStartPrice.add(discountAddPrice.multiply(d_dayCount));
    }
}
