package christmas.v1.order.event;

import christmas.v1.Gift;
import christmas.v1.Money;

import java.util.ArrayList;
import java.util.List;

public class EventResult {
    private Money totalDiscountAmount;
    private List<Gift> gifts;
    private List<EventBenefit> benefits;

    public EventResult() {
        this.totalDiscountAmount = Money.ZERO;
        benefits = new ArrayList<>();
    }

    public void addBenefit(EventBenefit benefit) {
        benefits.add(benefit);
    }


    public String getFormattedAppliedEventResults() {
        StringBuilder sb = new StringBuilder("<혜택 내역>\n");
        for (EventBenefit benefit : benefits) {
            sb.append(benefit.getDescription()).append("\n");
        }
        return sb.toString();
    }

    public void addDiscountAmount(Money discountAmount) {
        this.totalDiscountAmount = this.totalDiscountAmount.add(discountAmount);
    }

    public void addGift(Gift gift) {
        gifts.add(gift);
    }
}
