package christmas.v1.order.policy.rule.condition;

import christmas.v1.common.Money;
import christmas.v1.order.Order;
import christmas.v1.order.policy.rule.EventCondition;

public class StarBadgeCondition implements EventCondition{
    private static final Money THRESHOLD = Money.won(5_000);

    public static StarBadgeCondition create() {
        return new StarBadgeCondition();
    }

    @Override
    public boolean isSatisfiedBy(Order order) {
        Money totalBenefitAmount = order.calculateTotalBenefitAmount();
        return totalBenefitAmount.isGreaterThanOrEqual(THRESHOLD);
    }
}
