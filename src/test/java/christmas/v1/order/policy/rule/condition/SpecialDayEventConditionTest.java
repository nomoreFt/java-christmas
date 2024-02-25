package christmas.v1.order.policy.rule.condition;

import christmas.config.UnitTest;
import christmas.v1.order.Order;
import christmas.v1.order.policy.rule.calculator.EventCalendar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.when;

@UnitTest
@DisplayName("Domain > Order > 할인 EventPolicy > 할인 Rule > 할인 조건 > 특별한 날 Condition")
class SpecialDayEventConditionTest {
    @Mock
    EventCalendar eventCalendar;
    @Mock
    Order order;
    @InjectMocks
    SpecialDayEventCondition specialDayEventCondition;

    @DisplayName("특별한 날에 주문한 경우 참을 반환한다.")
    @Test
    void whenOrderedDateIsSpecialDayThenReturnTrue(){
        //given
        when(eventCalendar.isSpecialDay(any())).thenReturn(true);
        //when
        boolean result = specialDayEventCondition.isSatisfiedBy(order);

        //then
        Assertions.assertTrue(result);

    }

}