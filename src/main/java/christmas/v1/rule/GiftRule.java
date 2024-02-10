package christmas.v1.rule;

import christmas.v1.Gift;
import christmas.v1.order.Order;
import christmas.v1.order.event.GiftBenefit;

public final class GiftRule {
    private EventCondition condition;
    private RuleDescription benefitDescription;
    private Gift gift;

    private GiftRule(EventCondition condition, RuleDescription benefitDescription, Gift gift) {
        this.condition = condition;
        this.benefitDescription = benefitDescription;
        this.gift = gift;
    }

    public static GiftRule of(EventCondition condition, RuleDescription benefitDescription, Gift gift) {
        return new GiftRule(condition, benefitDescription, gift);
    }

    public Gift calculateGift(Order order) {
        if (condition.isSatisfiedBy(order)) {
            //혜택 내역 추가
            order.addBenefit(GiftBenefit.createWith(this.benefitDescription, gift));
            return gift;
        }
        return Gift.NONE;
    }
}
