package christmas.v1.order.policy.rule.condition;

import christmas.v1.common.Money;
import christmas.v1.order.Order;
import christmas.v1.order.policy.rule.EventCondition;

public class TreeBadgeCondition implements EventCondition{
    private static final Money THRESHOLD = Money.won(10_000);


    public static TreeBadgeCondition create() {
        return new TreeBadgeCondition();
    }

    @Override
    public boolean isSatisfiedBy(Order order) {
        Money totalBenefitAmount = order.calculateTotalBenefitAmount();
        return totalBenefitAmount.isGreaterThanOrEqual(THRESHOLD);
    }
}
