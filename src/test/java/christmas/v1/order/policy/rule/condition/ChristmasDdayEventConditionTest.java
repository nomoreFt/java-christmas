package christmas.v1.order.policy.rule.condition;

import christmas.config.UnitTest;
import christmas.v1.order.Order;
import christmas.v1.order.policy.rule.calculator.EventCalendar;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.when;

@UnitTest
@DisplayName("Domain > Order > 할인 EventPolicy > 할인 Rule > 할인 조건 > 크리스마스 D-day Condtiion")
class ChristmasDdayEventConditionTest {
    @Mock
    EventCalendar eventCalendar;
    @Mock
    Order order;
    @InjectMocks
    ChristmasDdayEventCondition christmasDdayEventCondition;


    @DisplayName("주문일자가 크리스마스 D-day 이벤트 기간에 포함되면 true를 반환한다.")
    @Test
    void whenOrderDateIsBetweenChristmasDdayEventThenReturnTrue() {
        //given
        when(eventCalendar.isBeforeChristmas(any())).thenReturn(true);

        //when
        boolean result = christmasDdayEventCondition.isSatisfiedBy(order);

        //then
        assertTrue(result);
    }

    @DisplayName("주문일자가 크리스마스 D-day 이벤트 기간에 포함되지 않으면 false를 반환한다.")
    @Test
    void whenOrderDateIsNotBetweenChristmasDdayEventThenReturnFalse() {
        //given
        when(eventCalendar.isBeforeChristmas(any())).thenReturn(false);

        //when
        boolean result = christmasDdayEventCondition.isSatisfiedBy(order);

        //then
        assertFalse(result);
    }



}