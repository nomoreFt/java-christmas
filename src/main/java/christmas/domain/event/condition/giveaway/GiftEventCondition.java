package christmas.domain.event.condition.giveaway;

import christmas.domain.Reservation;
import christmas.domain.event.common.Gift;

public interface GiftEventCondition {
    boolean isSatisfiedBy(Reservation reservation);

    Gift getGift(Reservation reservation);
}
