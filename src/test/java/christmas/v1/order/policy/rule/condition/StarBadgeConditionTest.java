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
@DisplayName("Domain > Order > 뱃지 EventPolicy > 뱃지 Rule > 뱃지 조건 > 별 배지 Condition")
class StarBadgeConditionTest {

    @Mock
    Order order;
    @InjectMocks
    StarBadgeCondition starBadgeCondition;

    @DisplayName("전체 혜택 금액이 5,000원 이상일 경우 참을 반환한다.")
    @Test
    void whenBenefitAmountMoreThan5_000ThenReturnTrue() {
        // given
        Money totalBenefitAmount = Money.won(5_000);
        BDDMockito.when(order.calculateTotalBenefitAmount()).thenReturn(totalBenefitAmount);
        // when
        boolean result = starBadgeCondition.isSatisfiedBy(order);
        //then
        assertTrue(result);
    }

    @DisplayName("전체 혜택 금액이 5,000원 미만일 경우 거짓을 반환한다.")
    @Test
    void whenBenefitAmountLessThan5_000ThenReturnFalse() {
        // given
        Money totalBenefitAmount = Money.won(4_999);
        BDDMockito.when(order.calculateTotalBenefitAmount()).thenReturn(totalBenefitAmount);
        // when
        boolean result = starBadgeCondition.isSatisfiedBy(order);
        //then
        assertFalse(result);
    }

}