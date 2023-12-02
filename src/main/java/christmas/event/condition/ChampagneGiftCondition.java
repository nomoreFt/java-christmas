package christmas.event.condition;

import christmas.common.Money;
import christmas.restaurant.OrderContext;

public class ChampagneGiftCondition implements EventCondition{
    private static final Money CHAMPAGNE_PRICE = Money.of(120_000);
    @Override
    public boolean isSatisfied(OrderContext order) {
        return order.getTotalFoodCost().isGreaterThanOrEqual(CHAMPAGNE_PRICE);
    }
}
