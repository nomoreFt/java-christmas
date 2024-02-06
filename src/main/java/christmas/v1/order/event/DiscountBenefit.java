package christmas.v1.order.event;

import christmas.v1.Money;
import christmas.v1.rule.BenefitDescription;

public class DiscountBenefit implements EventBenefit{
    private String eventDescription;
    private Money benefit;

    public DiscountBenefit(String eventDescription, Money benefit){
        this.benefit = benefit;
    }

    public static DiscountBenefit of(BenefitDescription benefitDescription, Money benefit){
        return new DiscountBenefit(benefitDescription.getDescription(), benefit);
    }



    @Override
    public String getDescription() {
        return eventDescription+ ": -" + benefit.toString() + "원";
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
