package christmas.domain.event.condition.gift;

import christmas.domain.EventCalendar;
import christmas.domain.event.common.Gift;
import christmas.domain.event.common.Money;

public interface GiftEventCondition {
    boolean isSatisfiedBy(Money orderAmount);
    Gift getGift(Money orderAmount);
}
