package christmas.v1.order.policy.rule.condition;

import christmas.config.UnitTest;
import christmas.v1.common.Money;
import christmas.v1.order.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertTrue;

@UnitTest
@DisplayName("Domain > Order > 선물 EventPolicy > 선물 Rule > 증정조건 > 샴페인 Condition")
class ChampagneGiftConditionTest {

    @Mock
    Order order;
    @InjectMocks
    ChampagneGiftCondition champagneGiftCondition;

    @DisplayName("주문 금액이 120_000원 이상이면 true를 반환한다.")
    @Test
    void whenOrderAmountIsGreaterThanOrEqual120_000WonThenReturnTrue() {
        //given
        BDDMockito.when(order.calculateBeforeDiscountAmount()).thenReturn(Money.won(120_000));

        //when
        boolean result = champagneGiftCondition.isSatisfiedBy(order);

        //then
        assertTrue(result);
    }

    @DisplayName("주문 금액이 120_000원 미만이면 false를 반환한다.")
    @Test
    void whenOrderAmountIsLessThan120_000WonThenReturnFalse() {
        //given
        BDDMockito.when(order.calculateBeforeDiscountAmount()).thenReturn(Money.won(119_999));

        //when
        boolean result = champagneGiftCondition.isSatisfiedBy(order);

        //then
        assertTrue(!result);
    }

}