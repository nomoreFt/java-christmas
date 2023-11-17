package christmas.domain.event.policy;

import christmas.domain.Reservation;
import christmas.domain.event.common.Gift;
import christmas.domain.event.condition.giveaway.GiftEventCondition;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

public class GiftPolicy implements EventPolicy{

    private Set<GiftEventCondition> giftEventConditions = new LinkedHashSet<>();

    public GiftPolicy(GiftEventCondition... giftEventConditions) {
        this.giftEventConditions = new LinkedHashSet<>(Arrays.asList(giftEventConditions));
    }

    @Override
    public GiftResult apply(Reservation reservation) {
        HashMap<GiftEventCondition, Gift> giftMap = new HashMap<>();
        for (GiftEventCondition condition : giftEventConditions) {
            if (condition.isSatisfiedBy(reservation)) {
                Gift gift = calculateGift(reservation, condition);
                giftMap.put(condition, gift);
            }
        }

        return new GiftResult(giftMap);
    }

    private Gift calculateGift(Reservation reservation, GiftEventCondition condition) {
        // 할인 금액 계산 로직
        return condition.getGift(reservation);
    }

}
