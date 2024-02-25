package christmas.v1.order.policy.rule.calculator;

import christmas.config.UnitTest;
import christmas.v1.common.Money;
import christmas.v1.order.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@UnitTest
@DisplayName("Domain > Order > 할인 EventPolicy > 할인 Rule > 특별한 날 calculator")
class SpecialDayDiscountCalculatorTest {

    @Mock
    Order order;
    @InjectMocks
    SpecialDayDiscountCalculator calculator;

    @DisplayName("특별한 날 할인 금액을 반환한다.")
    @Test
    void givenOrderThenReturnDiscountAmount() {
        Money expectedDiscountAmount = Money.won(1_000);
        // when
        Money discountAmount = calculator.calculateDiscount(order);
        // then
        Assertions.assertEquals(expectedDiscountAmount, discountAmount);
    }
}