package christmas.v1.rule;

import christmas.v1.Money;
import christmas.v1.Order;

public final class DiscountRule {
    //Rule에서 변경되는 것은 할인 조건 + 할인 금액 계산
    private DiscountCondition condition;
    private DiscountCalculator calculator;

    public DiscountRule(DiscountCondition condition, DiscountCalculator calculator){
        this.condition = condition;
        this.calculator = calculator;
    }
    public Money calculateDiscount(Order order){
        if(condition.isSatisfiedBy(order)){
            return calculator.calculateDiscount(order);
        }
        return Money.ZERO;
    }
}
