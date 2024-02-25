package christmas.v1.order.policy.rule;

import christmas.config.UnitTest;
import christmas.v1.common.Money;
import christmas.v1.order.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@UnitTest
@DisplayName("Domain > Order > 할인 EventPolicy > 할인 Rule")
class DiscountRuleTest {

    @Mock
    EventCondition condition;
    @Mock
    DiscountCalculator calculator;
    @Mock
    RuleDescription description;
    @Mock
    Order order;
    @InjectMocks
    DiscountRule discountRule;

    @DisplayName("할인 조건이 만족되면 Order에 혜택을 추가하고 할인 금액을 반환한다.")
    @Test
    void shouldAddBenefitToOrderWhenSatisfied() {
        Money expectDiscount = Money.won(1000);

        //given
        when(condition.isSatisfiedBy(any(Order.class))).thenReturn(true);
        when(calculator.calculateDiscount(any(Order.class))).thenReturn(expectDiscount);

        //when
        Money result = discountRule.calculateDiscount(order);

        //then
        verify(calculator, times(1)).calculateDiscount(order);
        verify(order, times(1)).addBenefit(any());
        Assertions.assertEquals(expectDiscount, result);
    }

    @DisplayName("할인 조건이 만족되지 않으면 0원을 반환한다.")
    @Test
    void shouldReturnZeroWhenNotSatisfied() {
        //given
        when(condition.isSatisfiedBy(any(Order.class))).thenReturn(false);

        //when
        Money result = discountRule.calculateDiscount(order);

        //then
        verify(calculator, times(0)).calculateDiscount(order);
        verify(order, times(0)).addBenefit(any());
        Assertions.assertEquals(Money.ZERO, result);
    }



}