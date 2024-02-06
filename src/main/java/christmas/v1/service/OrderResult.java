package christmas.v1.service;

import christmas.v1.Badge;
import christmas.v1.GiftItem;
import christmas.v1.Money;
import christmas.v1.order.Order;
import christmas.v1.order.OrderItem;
import christmas.v1.order.event.DiscountBenefit;
import christmas.v1.order.event.EventBenefit;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class OrderResult {
    private LocalDate orderedDate;
    private List<OrderItem> orderList;
    private Money totalBeforeDiscount;
    private List<GiftItem> gifts;
    private List<EventBenefit> benefits;
    private Badge badge;

    private OrderResult(Order order) {
        this.orderedDate = order.getOrderedDate();
        this.orderList = order.getOrderItems();
        this.totalBeforeDiscount = order.calculateBeforeDiscountAmount();
        this.gifts = order.calculateAppliedGifts();
        this.benefits = order.calculateAppliedBenefits();
        this.badge = order.calculateAppliedBadge();
    }

    public static OrderResult from(Order order) {
        return new OrderResult(order);
    }

    public LocalDate getOrderedDate() {
        return orderedDate;
    }

    public List<OrderItem> getOrderList() {
        return Collections.unmodifiableList(orderList);
    }

    public Money getTotalBeforeDiscount() {
        return totalBeforeDiscount;
    }

    public List<GiftItem> getGifts() {
        return Collections.unmodifiableList(gifts);
    }

    public Money calculateAfterDiscountAmount() {
        //DiscountBenefit만
        return benefits.stream()
                .filter(benefit -> benefit instanceof DiscountBenefit)
                .map(EventBenefit::getDiscountAmount)
                .reduce(Money.ZERO, Money::add);
    }

    public Money calculateBenefitAmount() {
        return benefits.stream()
                .map(EventBenefit::getBenefitAmount)
                .reduce(Money.ZERO, Money::add);
    }
    public Badge getBadge() {
        return Badge.calculateBadge(calculateBenefitAmount());
    }

    public List<EventBenefit> getBenefits() {
        return Collections.unmodifiableList(benefits);
    }
}
