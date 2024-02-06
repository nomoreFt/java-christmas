package christmas.v1.order.event;

import christmas.v1.Money;

public interface EventBenefit {
    String getDescription();
    Money getBenefitAmount();
    Money getDiscountAmount();
}
