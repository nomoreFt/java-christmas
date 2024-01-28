package christmas.v1.policy;

import christmas.v1.EventPolicy;
import christmas.v1.order.Order;
import christmas.v1.rule.BadgeRule;

public final class BadgeEventPolicy implements EventPolicy {
    private BadgeRule rule;

    public BadgeEventPolicy(BadgeRule rule) {
        this.rule = rule;
    }

    @Override
    public void applyEvent(Order order) {
        //rule.
        applyBadge(order);
    }

    private void applyBadge(Order order) {
    }
}
