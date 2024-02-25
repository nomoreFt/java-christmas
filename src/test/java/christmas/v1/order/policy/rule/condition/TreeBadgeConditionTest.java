package christmas.v1.order.policy.rule.condition;

import christmas.config.UnitTest;
import christmas.v1.common.Money;
import christmas.v1.order.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@UnitTest
@DisplayName("Domain > Order > 뱃지 EventPolicy > 뱃지 Rule > 뱃지 조건 > 트리 배지 Condition")
class TreeBadgeConditionTest {

    @Mock
    Order order;
    @InjectMocks
    TreeBadgeCondition treeBadgeCondition;

    @DisplayName("총 혜택 금액이 10_000원을 넘을 경우 참을 반환한다.")
    @Test
    void whenTotalBenefitAmountIsGreaterThan10_000ThenReturnTrue() {
        // given
        Money totalBenefitAmount = Money.won(10_001);
        BDDMockito.when(order.calculateTotalBenefitAmount()).thenReturn(totalBenefitAmount);
        // when
        boolean result = treeBadgeCondition.isSatisfiedBy(order);
        // then
        assertTrue(result);
    }

    @DisplayName("총 혜택 금액이 10_000원미만일 경우 거짓을 반환한다.")
    @Test
    void whenTotalBenefitAmountLessThan10_000ThenReturnFalse() {
        // given
        Money totalBenefitAmount = Money.won(9_999);
        BDDMockito.when(order.calculateTotalBenefitAmount()).thenReturn(totalBenefitAmount);
        // when
        boolean result = treeBadgeCondition.isSatisfiedBy(order);
        // then
        assertFalse(result);
    }

}