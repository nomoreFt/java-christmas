package christmas.v1.order.event;

import christmas.v1.Gift;
import christmas.v1.GiftItem;
import christmas.v1.Money;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventResult {
    private Money totalDiscountAmount;
    private List<GiftItem> gifts;
    private List<EventBenefit> benefits;

    public EventResult() {
        this.totalDiscountAmount = Money.ZERO;
        benefits = new ArrayList<>();
    }

    public void addBenefit(EventBenefit benefit) {
        benefits.add(benefit);
    }

    public void addDiscountAmount(Money discountAmount) {
        this.totalDiscountAmount = this.totalDiscountAmount.add(discountAmount);
    }

    public void addGift(Gift gift) {
        for (GiftItem giftItem : gifts) {
            if (giftItem.getGift() == gift) {
                // 이미 같은 종류의 선물이 목록에 있으면 개수만 증가
                int newQuantity = giftItem.getQuantity() + 1;
                gifts.set(gifts.indexOf(giftItem), new GiftItem(gift, newQuantity));
                return;
            }
        }
        // 새로운 종류의 선물이면 목록에 추가
        gifts.add(new GiftItem(gift, 1));
    }

    public List<GiftItem> getGifts() {
        return Collections.unmodifiableList(gifts);
    }

    public List<EventBenefit> getBenefits() {
        return Collections.unmodifiableList(benefits);
    }

    public Money getTotalBenefitPrice() {
        return benefits.stream()
                .map(EventBenefit::getBenefitAmount)
                .reduce(Money.ZERO, Money::add);
    }
}
