package christmas.dto;

import christmas.domain.EventResult;
import christmas.domain.event.common.Gift;

import java.util.HashMap;

public class GiftResult extends EventResult {
    private final HashMap<Gift, Integer> giftMap;

    public GiftResult(HashMap<Gift, Integer> giftMap) {
        this.giftMap = giftMap;
    }

    public HashMap<Gift, Integer> getGiftMap() {
        return giftMap;
    }
}
