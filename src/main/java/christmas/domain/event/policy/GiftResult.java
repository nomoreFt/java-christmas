package christmas.domain.event.policy;

import christmas.domain.event.common.Gift;
import christmas.domain.event.condition.giveaway.GiftEventCondition;

import java.util.HashMap;

public class GiftResult {
    public GiftResult(HashMap<GiftEventCondition, Gift> giftMap) {
    }
}
