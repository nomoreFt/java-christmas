package christmas.v1.rule;

import christmas.v1.Badge;
import christmas.v1.order.Order;

public class BadgeRule {
    private EventCondition condition;
    private final Badge badge;

    private BadgeRule(EventCondition condition, Badge badge) {
        this.condition = condition;
        this.badge = badge;
    }

    public static BadgeRule of(EventCondition condition, Badge badge) {
        return new BadgeRule(condition, badge);
    }

    public Badge calculateBadge(Order order) {
        return condition.isSatisfiedBy(order) ? badge : Badge.NONE;
    }
}
