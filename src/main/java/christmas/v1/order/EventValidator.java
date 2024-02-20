package christmas.v1.order;

import christmas.v1.common.Money;

public class EventValidator {
    // 최소 주문 금액을 상수로 정의
    private static final Money MIN_ORDER_AMOUNT_FOR_EVENT = Money.won(10000);

    // 주문 금액이 이벤트 적용 기준을 충족하는지 검사하는 메서드
    public boolean isEligibleForEvent(Order order) {
        Money orderAmount = order.calculateBeforeDiscountAmount();
        return orderAmount.isGreaterThanOrEqual(MIN_ORDER_AMOUNT_FOR_EVENT);
    }
}
