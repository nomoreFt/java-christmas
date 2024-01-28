package christmas.v1.policy;

import christmas.v1.EventPolicy;
import christmas.v1.order.Order;
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
        //order에 gift 추가
        rules.stream()
                .map(rule -> rule.calculateGift(order))
                .forEach(gift -> order.addGift(gift));

    }
}
