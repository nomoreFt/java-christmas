package christmas.v1.order;

import christmas.v1.EventPolicy;
import christmas.v1.Gift;
import christmas.v1.Money;
import christmas.v1.menu.MenuType;
import christmas.v1.order.event.EventBenefit;
import christmas.v1.order.event.EventResult;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private LocalDate orderedDate;//주문날짜
    private List<OrderItem> items;//주문 항목 목록
    private EventResult eventResult;//할인 결과

    protected Order(){}
    private Order(LocalDate orderedDate, List<OrderItem> items) {
        this.orderedDate = orderedDate;
        this.items = new ArrayList<>(items);
        this.eventResult = new EventResult();
    }
    public static Order of(LocalDate orderedDate, List<OrderItem> items) {
        return new Order(orderedDate, items);
    }


    //할인 전 총 주문 금액
    public Money getTotalAmounts() {
        return items.stream()
                .map(OrderItem::getPrice)
                .reduce(Money.ZERO, Money::add);
    }


    public LocalDate getOrderedDate() {
        return orderedDate;
    }

    //메뉴 타입으로 주문 항목 개수를 구한다.
    public long countOrderItemBy(MenuType menuType) {
        return items.stream()
                .filter(orderItem -> orderItem.isSameType(menuType))
                .count();
    }

    //할인 정책을 적용한다.
    public void applyEvent(EventPolicy eventPolicy) {
        eventPolicy.applyEvent(this); // 이벤트 적용
        // 이벤트 적용 후 필요한 경우 추가 로직 구현
    }

    public void addBenefit(EventBenefit benefit) {
        eventResult.addBenefit(benefit);
    }

    public void addGift(Gift gift) {
        eventResult.addGift(gift);
    }

    //총 할인액 추가
    public void addTotalDiscountAmount(Money discountAmount) {
        eventResult.addDiscountAmount(discountAmount);
    }

    public List<OrderItem> getOrderItems() {
        return Collections.unmodifiableList(items);
    }
}
