package christmas.v0.event;

import christmas.v0.common.Champagne;
import christmas.v0.order.EventExpectation;
import christmas.v0.restaurant.OrderContext;

public class ChampagneGiftPolicy extends EventPolicy {
    @Override
    protected void applyTo(OrderContext orderContext, EventExpectation eventExpectation) {
        eventExpectation.addAppliedEvent(this);
        eventExpectation.addGift(Champagne.of(1));
    }
}
