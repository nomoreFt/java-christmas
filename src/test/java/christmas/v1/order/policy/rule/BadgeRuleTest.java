package christmas.v1.order.policy.rule;

import christmas.config.UnitTest;
import christmas.v1.order.Badge;
import christmas.v1.order.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.when;

@UnitTest
@DisplayName("Domain > Order > 뱃지 EventPolicy > 뱃지 Rule")
class BadgeRuleTest {

    @Mock
    EventCondition condition;
    @Mock
    Badge badge;
    @Mock
    Order order;
    @InjectMocks
    BadgeRule badgeRule;

    @DisplayName("이벤트 조건을 만족하면 배지를 추가한다.")
    @Test
    void shouldReturnBadgeWhenConditionIsSatisfied(){
        //given
        when(condition.isSatisfiedBy(any(Order.class))).thenReturn(true);

        //when
        Badge result = badgeRule.calculateBadge(order);

        //then
        Assertions.assertEquals(badge, result);
    }

    @DisplayName("이벤트 조건을 만족하지 못하면 NONE을 반환한다.")
    @Test
    void shouldReturnNoneWhenNotSatisfiedCondition(){
        //given
        when(condition.isSatisfiedBy(any(Order.class))).thenReturn(false);

        //when
        Badge result = badgeRule.calculateBadge(order);

        //then
        Assertions.assertEquals(Badge.NONE, result);
    }

}