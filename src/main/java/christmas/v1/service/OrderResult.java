package christmas.v1.service;

import christmas.v1.Badge;
import christmas.v1.GiftItem;
import christmas.v1.Money;
import christmas.v1.order.Order;
import christmas.v1.order.OrderItem;
import christmas.v1.order.event.DiscountBenefit;
import christmas.v1.order.event.EventBenefit;

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
}