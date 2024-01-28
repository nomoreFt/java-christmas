package christmas.v1.order.event;


import christmas.v1.Gift;
import christmas.v1.rule.BenefitDescription;

public class GiftBenefit implements EventBenefit {
    private String eventDescription;
    private Gift benefit;

    public GiftBenefit(String eventDescription, Gift benefit){

        this.benefit = benefit;
    }

    public static GiftBenefit of(BenefitDescription benefitDescription, Gift benefit){
        return new GiftBenefit(benefitDescription.getDescription(), benefit);
    }
    @Override
    public String getDescription() {
        return eventDescription + ": -" + benefit.getPrice() + "원)";
    }
}
