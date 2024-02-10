package christmas.v1.condition;

import christmas.v1.Money;
import christmas.v1.order.Order;
import christmas.v1.rule.EventCondition;

public class SantaBadgeCondition implements EventCondition{
    private static final Money THRESHOLD = Money.won(20_000);

    public static SantaBadgeCondition create() {
        return new SantaBadgeCondition();
    }

    @Override
    public boolean isSatisfiedBy(Order order) {
        Money totalBenefitAmount = order.calculateTotalBenefitAmount();
        return totalBenefitAmount.isGreaterThanOrEqual(THRESHOLD);
    }
}
