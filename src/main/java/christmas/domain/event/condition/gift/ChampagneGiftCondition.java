package christmas.domain.event.condition.gift;

import christmas.domain.event.common.Gift;
import christmas.domain.event.common.Money;

public class ChampagneGiftCondition implements GiftEventCondition {
    public static final Money CHAMPAGNE_PRICE = Money.wons(120000);
    @Override
    public boolean isSatisfiedBy(Money orderAmount) {
        return orderAmount.isGreaterThanOrEqual(CHAMPAGNE_PRICE);
    }

    @Override
    public Gift getGift(Money orderAmount) {
        if(isSatisfiedBy(orderAmount)) {
            return Gift.Champagne;
        }
        return Gift.Empty;
    }
}
