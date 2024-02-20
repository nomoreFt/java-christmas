package christmas.v1.order.policy.rule.calculator;

import christmas.v1.common.Money;
import christmas.v1.order.Order;
import christmas.v1.order.policy.rule.DiscountCalculator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ChristmasDdayDiscountCalculator implements DiscountCalculator {
    private static final Money START_DISCOUNT_AMOUNT = Money.won(1_000); // 할인 시작 금액
    private static final Money DAILY_INCREMENT_AMOUNT = Money.won(100); // 일일 증가 금액

    private EventCalendar eventCalendar;

    private ChristmasDdayDiscountCalculator(EventCalendar eventCalendar) {
        this.eventCalendar = eventCalendar;
    }

    public static ChristmasDdayDiscountCalculator create(EventCalendar eventCalendar) {
        return new ChristmasDdayDiscountCalculator(eventCalendar);
    }

    @Override
    public Money calculateDiscount(Order order) {
        LocalDate orderDate = order.getOrderedDate();
        LocalDate eventStartDate = eventCalendar.getEventStartDate();

        // 이벤트 시작일로부터 주문일까지의 일수 차이 계산
        long daysFromStart = ChronoUnit.DAYS.between(eventStartDate, orderDate);

        // 할인 금액 계산: 시작일에 START_DISCOUNT_AMOUNT원, 이후 매일 DAILY_INCREMENT_AMOUNT원씩 증가
        return START_DISCOUNT_AMOUNT.add(DAILY_INCREMENT_AMOUNT.multiply(daysFromStart));
    }
}
