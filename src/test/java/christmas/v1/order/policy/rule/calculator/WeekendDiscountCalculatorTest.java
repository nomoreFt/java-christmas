package christmas.v1.order.policy.rule.calculator;

import christmas.config.UnitTest;
import christmas.v1.common.Money;
import christmas.v1.order.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@UnitTest
@DisplayName("Domain > Order > 할인 EventPolicy > 할인 Rule > 주말 할인 calculator")
class WeekendDiscountCalculatorTest {

    @Mock
    Order order;

    private static Stream<Arguments> provideTestParameters() {
        return Stream.of(
                Arguments.of(0, Money.ZERO),
                Arguments.of(1, Money.won(2023)),
                Arguments.of(2, Money.won(4046)),
                Arguments.of(3, Money.won(6069))
        );
    }

    @DisplayName("메인메뉴 1개당 2,023원을 추가 할인 해준다.")
    @ParameterizedTest(name = "#{index} - 메인 주문 개수: {0}, 기대되는 할인 금액: {1}")
    @MethodSource("provideTestParameters")
    void givenMainQuantityThenReturnDiscountAmount(long mainQuantity, Money expectedDiscountAmount) {
        // given
        given(order.countOrderItemBy(any())).willReturn(mainQuantity);
        // when
        Money discountAmount = WeekendDiscountCalculator.create().calculateDiscount(order);
        // then
        Assertions.assertEquals(expectedDiscountAmount, discountAmount);
    }

}