package christmas.v1.policy;

import christmas.v1.EventPolicy;
import christmas.v1.Money;
import christmas.v1.Order;
import christmas.v1.rule.DiscountRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class DiscountEventPolicy implements EventPolicy {
    private List<DiscountRule> discountRules;

    //리스트 불변 유지
    public DiscountEventPolicy(DiscountRule... initialRules) {
        discountRules = new ArrayList<>();
        addRule(initialRules);
    }

    private void addRule(DiscountRule... rules) {
        this.discountRules.addAll(Arrays.asList(rules));
    }

    @Override
    public void applyEvent(Order order) {
        Money discountAmount = discountRules
                .stream()
                .map(rule -> rule.calculateDiscount(order))
                .reduce(Money.ZERO, Money::add);
        order.addDiscount(discountAmount);
    }
}
