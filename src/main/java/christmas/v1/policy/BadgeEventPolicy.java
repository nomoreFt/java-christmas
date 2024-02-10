package christmas.v1.policy;

import christmas.v1.Badge;
import christmas.v1.EventPolicy;
import christmas.v1.order.Order;
import christmas.v1.rule.BadgeRule;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class BadgeEventPolicy implements EventPolicy {
    private List<BadgeRule> rules = new ArrayList<>();

    private BadgeEventPolicy(BadgeRule... initialRules) {
        addRule(initialRules);
    }

    public static BadgeEventPolicy of(BadgeRule... initialRules) {
        return new BadgeEventPolicy(initialRules);
    }

    public void addRule(BadgeRule... rules) {
        this.rules.addAll(List.of(rules));
    }

    @Override
    public void applyEvent(Order order) {
        Badge hightesBadge = rules.stream()
                .map(rule -> rule.calculateBadge(order))
                .max(Comparator.comparingInt(Badge::getPriority))
                .orElse(Badge.NONE);

        order.assignBadge(hightesBadge);
    }
}
