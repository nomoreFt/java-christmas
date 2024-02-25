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
@DisplayName("Domain > Order > 뱃지 EventPolicy > 뱃지 Rule > 뱃지 조건 > 산타 배지 Condition")
class SantaBadgeConditionTest {
    @Mock
    Order order;
    @InjectMocks
    SantaBadgeCondition santaBadgeCondition;

    @DisplayName("전체 혜택 금액이 20,000원 이상일 경우 참을 반환한다.")
    @Test
    void whenTotalBenefitAmountIsGreaterThanOrEqualThresholdThenReturnTrue() {
        // given
        Money totalBenefitAmount = Money.won(20_000);
        BDDMockito.when(order.calculateTotalBenefitAmount()).thenReturn(totalBenefitAmount);
        // when
        boolean result = santaBadgeCondition.isSatisfiedBy(order);
        // then
        assertTrue(result);
    }

    @DisplayName("전체 혜택 금액이 20,000원 미만일 경우 거짓을 반환한다.")
    @Test
    void whenTotalBenefitAmountIsLessThanThresholdThenReturnFalse() {
        // given
        Money totalBenefitAmount = Money.won(19_999);
        BDDMockito.when(order.calculateTotalBenefitAmount()).thenReturn(totalBenefitAmount);
        // when
        boolean result = santaBadgeCondition.isSatisfiedBy(order);
        // then
        assertTrue(!result);
    }

}