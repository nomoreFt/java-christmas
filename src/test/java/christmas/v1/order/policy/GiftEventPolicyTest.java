package christmas.v1.order.policy;

import christmas.config.UnitTest;
import christmas.v1.order.Gift;
import christmas.v1.order.Order;
import christmas.v1.order.policy.rule.GiftRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.verify;
import static org.mockito.BDDMockito.when;
import static org.mockito.Mockito.never;

@UnitTest
@DisplayName("Domain > Order > 선물 EventPolicy")
class GiftEventPolicyTest {
    @Mock
    GiftRule giftRule1;
    @Mock
    GiftRule giftRule2;
    @Mock
    Order order;

    @DisplayName("선물 규칙이 없을 때 선물이 없어야 한다")
    @Test
    void shouldApplyNoGiftWhenNoRulePresent(){
        //given
        GiftEventPolicy policy = GiftEventPolicy.of();
        //when
        policy.applyEvent(order);
        //then
        verify(order, never()).addGift(any());
    }

    @Test
    @DisplayName("적절한 선물이 주문에 추가되어야 한다")
    void shouldAddCorrectGiftToOrder() {
        // 규칙에 따라 선물 계산
        when(giftRule1.calculateGift(order)).thenReturn(Gift.CHAMPAGNE);
        when(giftRule2.calculateGift(order)).thenReturn(Gift.NONE);

        // GiftEventPolicy 인스턴스 생성 및 규칙 추가
        GiftEventPolicy policy = GiftEventPolicy.of(giftRule1, giftRule2);

        // 이벤트 적용
        policy.applyEvent(order);

        // 검증: 주문에 적절한 선물이 추가되었는지 확인
        verify(order).addGift(Gift.CHAMPAGNE);
    }

    @Test
    @DisplayName("Gift.NONE은 주문에 추가되지 않아야 한다")
    void shouldNotAddNoneGiftToOrder() {
        // Gift.NONE을 반환하는 규칙
        when(giftRule1.calculateGift(order)).thenReturn(Gift.NONE);

        // GiftEventPolicy 인스턴스 생성 및 규칙 추가
        GiftEventPolicy policy = GiftEventPolicy.of(giftRule1);

        // 이벤트 적용
        policy.applyEvent(order);

        // 검증: Gift.NONE이 주문에 추가되지 않았는지 확인
        verify(order, never()).addGift(Gift.NONE);
    }

}