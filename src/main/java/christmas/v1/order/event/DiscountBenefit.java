package christmas.v1.order.event;

import christmas.v1.Money;
import christmas.v1.rule.RuleDescription;

public class DiscountBenefit implements EventBenefit{
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

    @Override
    public Money getDiscountAmount() {
        return benefit;
    }
}
