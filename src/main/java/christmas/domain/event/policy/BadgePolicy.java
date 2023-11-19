package christmas.domain.event.policy;

import christmas.domain.EventResult;
import christmas.domain.event.common.Badge;
import christmas.domain.event.common.Money;
import christmas.domain.event.condition.badge.BadgeEventCondition;
import christmas.dto.BadgeResult;

public class BadgePolicy implements EventPolicy {
    private BadgeEventCondition badgeEventCondition;

    public BadgePolicy(BadgeEventCondition badgeEventCondition) {
        this.badgeEventCondition = badgeEventCondition;
    }

    @Override
    public EventResult apply(EventContext context) {
        Money totalDiscountAmount = context.getTotalDiscountAmount();
        // 뱃지 로직 구현
        if (totalDiscountAmount.isGreaterThanOrEqual(Money.wons(20000))) {
            return new BadgeResult(Badge.Santa);
        }

        if(totalDiscountAmount.isGreaterThanOrEqual(Money.wons(10000))) {
            return new BadgeResult(Badge.Tree);
        }

        if(totalDiscountAmount.isGreaterThanOrEqual(Money.wons(5000))) {
            return new BadgeResult(Badge.Star);
        }

        return new BadgeResult(Badge.Empty);
    }
}
