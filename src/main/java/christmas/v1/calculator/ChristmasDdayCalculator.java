package christmas.v1.calculator;

import christmas.v1.EventCalendar;
import christmas.v1.Money;
import christmas.v1.Order;
import christmas.v1.rule.DiscountCalculator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ChristmasDdayCalculator implements DiscountCalculator {
    private static final long START_DISCOUNT_AMOUNT = 1000; // 할인 시작 금액
    private static final long DAILY_INCREMENT_AMOUNT = 100; // 일일 증가 금액

    private EventCalendar eventCalendar;

    public ChristmasDdayCalculator(EventCalendar eventCalendar) {
        this.eventCalendar = eventCalendar;
    }

    @Override
    public Money calculateDiscount(Order order) {
        LocalDate orderDate = order.getOrderedDate();
        LocalDate eventStartDate = eventCalendar.getEventStartDate();

        // 이벤트 시작일로부터 주문일까지의 일수 차이 계산
        long daysFromStart = ChronoUnit.DAYS.between(eventStartDate, orderDate);

        // 할인 금액 계산: 시작일에 START_DISCOUNT_AMOUNT원, 이후 매일 DAILY_INCREMENT_AMOUNT원씩 증가
        long discountAmount = START_DISCOUNT_AMOUNT + daysFromStart * DAILY_INCREMENT_AMOUNT;

        return Money.won(discountAmount);
    }
}
