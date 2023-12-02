package christmas.order;

import christmas.common.Money;
import christmas.event.EventPolicy;

import java.util.List;

public record EventExpectation(
        List<EventPolicy> applicableEvents,
        Money discountAmount,
        List<Gift> gifts
) {
    //of
    public static EventExpectation createEmpty() {
        return new EventExpectation(List.of(), Money.ZERO, List.of());
    }
}
