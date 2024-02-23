package christmas.v1.order.policy;

import christmas.v1.order.Badge;
import christmas.v1.order.Order;
import christmas.v1.order.policy.rule.BadgeRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class BadgeEventPolicyTest {
    @Mock
    BadgeRule badgeRule1;
    @Mock
    BadgeRule badgeRule2;
    @Mock
    Order order;
    private BadgeEventPolicy badgeEventPolicy;
    @BeforeEach
    void setUp() {
        badgeEventPolicy = BadgeEventPolicy.of(badgeRule1, badgeRule2);
    }

    @DisplayName("가장 높은 등급의 뱃지만 주문에 추가되어야 한다")
    @Test
    void shouldApplyHighestBadgeToOrder() {
        // 뱃지 규칙에 따라 뱃지 계산
        when(badgeRule1.calculateBadge(any())).thenReturn(Badge.SANTA);
        when(badgeRule2.calculateBadge(any())).thenReturn(Badge.NONE);

        // 이벤트 적용
        badgeEventPolicy.applyEvent(order);

        // 검증: 주문에 가장 높은 등급의 뱃지만 추가되었는지 확인
        verify(order).assignBadge(Badge.SANTA);
    }

    @DisplayName("규칙이 없을 경우 NONE 뱃지가 할당되어야 한다.")
    @Test
    void shouldAssignNoneBadgeWhenNoRulesPresent() {
        // 규칙이 없는 경우
        BadgeEventPolicy policy = BadgeEventPolicy.of();

        // 이벤트 적용
        policy.applyEvent(order);

        // 검증: 주문에 NONE 뱃지가 추가되었는지 확인
        verify(order).assignBadge(Badge.NONE);
    }

    @DisplayName("모든 규칙이 NONE을 반환할 경우 NONE 뱃지가 할당되어야 한다.")
    @Test
    void shouldAssignNoneBadgeWhenAllRulesReturnNone() {
        // 모든 규칙이 NONE을 반환하는 경우
        when(badgeRule1.calculateBadge(any())).thenReturn(Badge.NONE);
        when(badgeRule2.calculateBadge(any())).thenReturn(Badge.NONE);

        // 이벤트 적용
        badgeEventPolicy.applyEvent(order);

        // 검증: 주문에 NONE 뱃지가 추가되었는지 확인
        verify(order).assignBadge(Badge.NONE);
    }

}