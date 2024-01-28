package christmas.v1.rule;

import christmas.v1.Gift;
import christmas.v1.order.Order;
import christmas.v1.order.event.GiftBenefit;

public class GiftRule {
    private EventCondition condition;
    private BenefitDescription benefitDescription;
    private GiftCalculator calculator;
    public Gift calculateGift(Order order) {
        if (condition.isSatisfiedBy(order)) {
            Gift gift = calculator.calculateGift(order);
            //혜택 내역 추가
            order.addBenefit(GiftBenefit.of(benefitDescription, gift));
            return gift;
        }
        return Gift.NONE;
    }
}
