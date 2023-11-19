package christmas.domain;

import christmas.domain.event.common.Money;
import christmas.domain.event.condition.discount.DiscountEventCondition;

public class DiscountDetail {
    private DiscountEventCondition condition;
    private Money discountAmount;

    private DiscountDetail(DiscountEventCondition condition, Money discountAmount) {
        this.condition = condition;
        this.discountAmount = discountAmount;
    }

    public static DiscountDetail of(DiscountEventCondition condition, Money discountAmount) {
        return new DiscountDetail(condition, discountAmount);
    }

    public Money getDiscountAmount() {
        return discountAmount;
    }
}
