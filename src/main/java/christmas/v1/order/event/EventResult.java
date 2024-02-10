package christmas.v1.order.event;

import christmas.v1.Badge;
import christmas.v1.Gift;
import christmas.v1.GiftItem;
import christmas.v1.Money;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventResult {
    private Money totalDiscountAmount = Money.ZERO;
    private List<GiftItem> gifts = new ArrayList<>();
    private List<EventBenefit> benefits = new ArrayList<>();
    private Badge badge = Badge.NONE;

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

    public void assignBadge(Badge hightesBadge) {
        if (hightesBadge.isHigherThan(badge)) {
            badge = hightesBadge;
        }
    }

    public Badge getBadge() {
        return badge;
    }
}
