package christmas.domain.event.condition.giveaway;

import christmas.domain.Reservation;
import christmas.domain.event.common.Gift;
import christmas.domain.event.common.Money;
import christmas.domain.event.condition.discount.DiscountEventCondition;

public class BadgeConditionDiscount implements GiftEventCondition {

    @Override
    public boolean isSatisfiedBy(Reservation reservation) {

    }

    @Override
    public Gift getGift(Reservation reservation) {
        return null;
    }
}
