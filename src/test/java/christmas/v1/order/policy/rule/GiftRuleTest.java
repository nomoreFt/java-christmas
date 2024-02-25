package christmas.v1.order.policy.rule;

import christmas.config.UnitTest;
import christmas.v1.order.Gift;
import christmas.v1.order.Order;
import christmas.v1.order.benefit.GiftBenefit;
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
@DisplayName("Domain > Order > 할인 EventPolicy > 증정 Rule")
class GiftRuleTest {

    @Mock
    EventCondition condition;
    @Mock
    RuleDescription description;
    @Mock
    Gift gift;
    @Mock
    Order order;
    @InjectMocks
    GiftRule giftRule;

    @DisplayName("선물 조건이 만족되면 Order에 혜택을 추가하고 선물을 반환한다.")
    @Test
    void shouldAddGiftToOrderWhenSatisfiedCondition(){
        //given
        when(condition.isSatisfiedBy(order)).thenReturn(true);

        //when
        Gift result = giftRule.calculateGift(order);

        //then
        Assertions.assertEquals(gift, result);
        verify(order, times(1)).addBenefit(any(GiftBenefit.class));

    }

    @DisplayName("선물 조건이 만족되지 않으면 NONE을 반환한다.")
    @Test
    void shouldReturnNoneWhenNotSatisfiedCondition(){
        //given
        when(condition.isSatisfiedBy(order)).thenReturn(false);

        //when
        Gift result = giftRule.calculateGift(order);

        //then
        Assertions.assertEquals(Gift.NONE, result);
        verify(order, times(0)).addBenefit(any());
    }

}