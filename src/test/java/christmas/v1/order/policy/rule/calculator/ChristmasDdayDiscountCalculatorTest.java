package christmas.v1.order.policy.rule.calculator;

import christmas.config.UnitTest;
import christmas.v1.common.Money;
import christmas.v1.order.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;

@UnitTest
@DisplayName("Domain > Order > 할인 EventPolicy > 할인 Rule > 크리스마스 calculator")
class ChristmasDdayDiscountCalculatorTest {
    @Mock
    EventCalendar eventCalendar;
    @Mock
    Order order;
    @InjectMocks
    ChristmasDdayDiscountCalculator calculator;

    private static Stream<Arguments> provideTestParameters() {
        return Stream.of(
                Arguments.of(LocalDate.of(2024, 12, 1), Money.won(1_000)), // 이벤트 시작일
                Arguments.of(LocalDate.of(2024, 12, 2), Money.won(1_100)), // 이벤트 시작일로부터 1일 후
                Arguments.of(LocalDate.of(2024, 12, 10), Money.won(1_900)), // 이벤트 시작일로부터 9일 후
                Arguments.of(LocalDate.of(2024, 12, 25), Money.won(3_400)) // 이벤트 시작일로부터 24일 후
                // 필요한 만큼 더 추가할 수 있습니다.
        );
    }

    @ParameterizedTest(name = "#{index} - 주문일: {0}, 기대되는 할인 금액: {1}")
    @MethodSource("provideTestParameters")
    @DisplayName("이벤트 시작일로부터 주문일까지의 일수 차이에 따라 할인 금액을 계산한다.")
    void calculateDiscount(LocalDate orderDate, Money expectedDiscountAmount) {
        // given
        given(order.getOrderedDate()).willReturn(orderDate);
        given(eventCalendar.getEventStartDate()).willReturn(LocalDate.of(2024, 12, 1));
        // when
        Money discountAmount = calculator.calculateDiscount(order);
        // then
        Assertions.assertEquals(expectedDiscountAmount, discountAmount);
    }


}