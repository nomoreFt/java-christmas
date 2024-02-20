package christmas.v1.order.benefit;

import christmas.v1.common.Money;
import christmas.v1.order.EventBenefit;
import christmas.v1.order.policy.rule.RuleDescription;

public class DiscountBenefit implements EventBenefit {
    private String eventDescription;
    private Money benefit;

    private DiscountBenefit(String eventDescription, Money benefit){
        this.eventDescription = eventDescription;
        this.benefit = benefit;
    }

    public static DiscountBenefit createWith(RuleDescription benefitDescription, Money benefit){
        return new DiscountBenefit(benefitDescription.getDescription(), benefit);
    }



    @Override
    public String getDescription() {
        return eventDescription;
    }

    @Override
    public Money getBenefitAmount() {
        return benefit;
    }

}
