package christmas.event;

import christmas.common.Champagne;
import christmas.order.EventExpectation;
import christmas.restaurant.OrderContext;

public class ChampagneGiftPolicy extends EventPolicy {
    @Override
    protected void applyTo(OrderContext orderContext, EventExpectation eventExpectation) {
        eventExpectation.addAppliedEvent(this);
        eventExpectation.addGift(Champagne.of(1));
    }
}
