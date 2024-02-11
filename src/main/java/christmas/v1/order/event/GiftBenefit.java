package christmas.v1.order.event;


import christmas.v1.Gift;
import christmas.v1.GiftItem;
import christmas.v1.Money;
import christmas.v1.rule.RuleDescription;

public class GiftBenefit implements EventBenefit {
    private String eventDescription;
    private GiftItem benefit;

    private GiftBenefit(String eventDescription, Gift benefit){
        this.eventDescription = eventDescription;
        this.benefit = new GiftItem(benefit, 1);
    }

    public static GiftBenefit createWith(RuleDescription benefitDescription, Gift benefit){
        return new GiftBenefit(benefitDescription.getDescription(), benefit);
    }

    @Override
    public String getDescription() {
        return eventDescription;
    }

    @Override
    public Money getBenefitAmount() {
        return benefit.getGiftPrice();
    }

    @Override
    public Money getDiscountAmount() {
        return Money.ZERO;
    }
}
