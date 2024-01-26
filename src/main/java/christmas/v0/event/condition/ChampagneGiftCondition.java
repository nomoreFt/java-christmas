package christmas.v0.event.condition;

import christmas.v0.common.Money;
import christmas.v0.event.EventCondition;
import christmas.v0.restaurant.OrderContext;

public class ChampagneGiftCondition implements EventCondition {
    private static final Money CHAMPAGNE_PRICE = Money.won(120_000);
    @Override
    public boolean isSatisfied(OrderContext order) {
        return order.getTotalFoodCost().isGreaterThanOrEqual(CHAMPAGNE_PRICE);
    }
}
