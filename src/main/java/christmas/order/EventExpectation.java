package christmas.order;

import christmas.common.Badge;
import christmas.common.Champagne;
import christmas.common.Gift;
import christmas.common.Money;
import christmas.event.ChristmasDdayEventPolicy;
import christmas.event.EventPolicy;

import java.util.List;

public class EventExpectation{

    private List<EventPolicy> applicableEvents;
    private Money discountAmount;
    private List<Gift> gifts;

    private EventExpectation(List<EventPolicy> applicableEvents, Money discountAmount, List<Gift> gifts) {
        this.applicableEvents = applicableEvents;
        this.discountAmount = discountAmount;
        this.gifts = gifts;
    }

    public static EventExpectation createEmpty() {
        return new EventExpectation(List.of(), Money.ZERO, List.of());
    }

    public void addDiscountAmount(Money discountAmount) {
        this.discountAmount = this.discountAmount.add(discountAmount);
    }

    public void addGift(Gift gift) {
        this.gifts.add(gift);
    }

    public Badge getBadge() {
        return Badge.calculateBadge(discountAmount);
    }

    public void addAppliedEvent(EventPolicy eventPolicy) {
        this.applicableEvents.add(eventPolicy);
    }
}
