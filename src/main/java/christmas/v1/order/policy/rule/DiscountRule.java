package christmas.v1.order.policy.rule;

import christmas.v1.common.Money;
import christmas.v1.order.Order;
import christmas.v1.order.benefit.DiscountBenefit;

public final class DiscountRule {
    //Rule에서 변경되는 것은 할인 조건 + 할인 금액 계산
    private EventCondition condition;
    private DiscountCalculator discountCalculator;
    private RuleDescription ruleDescription;

    private DiscountRule(EventCondition condition, DiscountCalculator discountCalculator, RuleDescription ruleDescription){
        this.condition = condition;
        this.discountCalculator = discountCalculator;
        this.ruleDescription = ruleDescription;
    }

    public static DiscountRule of(EventCondition condition, DiscountCalculator discountCalculator, RuleDescription ruleDescription){
        return new DiscountRule(condition, discountCalculator, ruleDescription);
    }
    public Money calculateDiscount(Order order){
        if(condition.isSatisfiedBy(order)){
            //개별 할인 금액 계산
            Money discountAmount = discountCalculator.calculateDiscount(order);
            //주문에 적용 할인 정책 + 금액 추가
            //discountAmount가 0이 아니면 할인 금액 추가
            if(discountAmount.isGreaterThan(Money.ZERO)){
                order.addBenefit(DiscountBenefit.createWith(this.ruleDescription, discountAmount));
            }
            return discountAmount;
        }
        return Money.ZERO;
    }
}
