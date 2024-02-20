package christmas.v1.service.dto;

import christmas.v1.order.Badge;
import christmas.v1.order.GiftItem;
import christmas.v1.common.Money;
import christmas.v1.order.Order;
import christmas.v1.order.OrderItem;
import christmas.v1.order.EventBenefit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record OrderResult(
        LocalDate orderedDate,
        List<OrderItem> orderList,
        Money totalBeforeDiscount,
        List<GiftItem> gifts,
        List<EventBenefit> benefits,

        Money totalBenefitAmount,
        Money totalAfterDiscount,
        Badge badge
) {

    public static OrderResult from(Order order) {
        return new OrderResult(
                order.getOrderedDate(),
                Collections.unmodifiableList(new ArrayList<>(order.getOrderItems())),
                order.calculateBeforeDiscountAmount(),
                Collections.unmodifiableList(new ArrayList<>(order.calculateAppliedGifts())),
                Collections.unmodifiableList(new ArrayList<>(order.calculateAppliedBenefits())),
                order.calculateTotalBenefitAmount(),
                order.calculateTotalAfterDiscount(),
                order.getAppliedBadge()
        );
    }

    //Gift가 None이 아니고 quantity가 0보다 크면 true
    public boolean hasGift() {
        return gifts.stream()
                .filter(giftItem -> giftItem.getQuantity() > 0)
                .anyMatch(GiftItem::existsGift);
    }

    public boolean hasBenefit() {
        return benefits.stream()
                .anyMatch(benefit -> benefit.getBenefitAmount().isGreaterThan(Money.ZERO));
    }

    public boolean hasBadge() {
        return badge != Badge.NONE;
    }
}