package christmas.domain.event.condition.badge;

import christmas.domain.event.common.Badge;
import christmas.domain.event.common.Money;

public class DecemberBadgeEventCondition implements BadgeEventCondition {
    public static final Money BADGE_CONDITION_AMOUNT = Money.wons(5000);

    @Override
    public boolean isSatisfiedBy(Money orderAmount) {
        return orderAmount.isGreaterThanOrEqual(BADGE_CONDITION_AMOUNT);

    }

    @Override
    public Badge getBadge(Money orderAmount) {
        if (orderAmount.isGreaterThanOrEqual(Money.wons(20000))) {
            return Badge.Santa;
        }
        if (orderAmount.isGreaterThanOrEqual(Money.wons(10000))) {
            return Badge.Tree;
        }
        if (orderAmount.isGreaterThanOrEqual(Money.wons(5000))) {
            return Badge.Star;
        }
        return Badge.Empty;
    }
}
