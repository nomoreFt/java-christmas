package christmas.v1.policy;

import christmas.v1.EventPolicy;
import christmas.v1.Order;
import christmas.v1.rule.GiftRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class GiftEventPolicy implements EventPolicy {
    private List<GiftRule> rules = new ArrayList<>();

    public void addRule(GiftRule... rules) {
        this.rules.addAll(Arrays.asList(rules));
    }


    @Override
    public void applyEvent(Order order) {

    }
}
