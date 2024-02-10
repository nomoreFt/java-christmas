package christmas.v1.policy;

import christmas.v1.EventPolicy;
import christmas.v1.Money;
import christmas.v1.order.Order;
import christmas.v1.rule.DiscountRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class DiscountEventPolicy implements EventPolicy {
    private List<DiscountRule> discountRules;

    //리스트 불변 유지
    private DiscountEventPolicy(DiscountRule... initialRules) {
        discountRules = new ArrayList<>();
        addRule(initialRules);
    }

    public static DiscountEventPolicy of(DiscountRule... initialRules) {
        return new DiscountEventPolicy(initialRules);
    }

    public void addRule(DiscountRule... rules) {
        this.discountRules.addAll(Arrays.asList(rules));
    }

    @Override
    public void applyEvent(Order order) {
        Money totalDiscountAmount = discountRules
                .stream()
                .map(rule -> rule.calculateDiscount(order))
                .reduce(Money.ZERO, Money::add);
        //총 할인 금액 추가
        order.addTotalDiscountAmount(totalDiscountAmount);
    }
}
