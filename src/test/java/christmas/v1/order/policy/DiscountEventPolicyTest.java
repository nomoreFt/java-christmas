package christmas.v1.order.policy;

import christmas.v1.common.Money;
import christmas.v1.order.Order;
import christmas.v1.order.policy.rule.DiscountRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.verify;
import static org.mockito.BDDMockito.when;

@ExtendWith(MockitoExtension.class)
class DiscountEventPolicyTest {
    @Mock DiscountRule discountRule;
    @Mock DiscountRule discountRule2;
    @Mock Order order;

    @Test
    @DisplayName("할인 규칙이 없을 때 총 할인액은 0이어야 한다")
    void shouldApplyNoDiscountWhenNoRulesPresent() {
        Money expect = Money.ZERO;
        DiscountEventPolicy policy = DiscountEventPolicy.of();
        policy.applyEvent(order);
        verify(order).addTotalDiscountAmount(expect);
    }

    @Test
    @DisplayName("적용된 할인규칙의 금액이 없으면, 총 할인액은 0이어야 한다")
    void shouldApplyNoDiscountMoneyWhenRulesNotReturnMoney() {
        Money expect = Money.ZERO;

        //given
        DiscountEventPolicy policy = DiscountEventPolicy.of(discountRule,discountRule2);
        when(discountRule.calculateDiscount(order)).thenReturn(expect);
        when(discountRule2.calculateDiscount(order)).thenReturn(expect);

        //when
        policy.applyEvent(order);

        //then
        verify(order).addTotalDiscountAmount(Money.ZERO);
    }

    @Test
    @DisplayName("적용된 할인규칙의 금액이 있으면, 총 할인액은 합산이어야 한다")
    void shouldApplyDiscountMoneyWhenRulesReturnMoney() {
        Money expect1 = Money.won(1000);
        Money expect2 = Money.won(5000);
        //given
        DiscountEventPolicy policy = DiscountEventPolicy.of(discountRule,discountRule2);
        when(discountRule.calculateDiscount(order)).thenReturn(expect1);
        when(discountRule2.calculateDiscount(order)).thenReturn(expect2);

        //when
        policy.applyEvent(order);

        //then
        verify(order).addTotalDiscountAmount(expect1.add(expect2));
    }




}