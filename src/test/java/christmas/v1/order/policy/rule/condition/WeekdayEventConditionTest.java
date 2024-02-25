package christmas.v1.order.policy.rule.condition;

import christmas.config.UnitTest;
import christmas.v1.order.Order;
import christmas.v1.order.policy.rule.calculator.EventCalendar;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@UnitTest
@DisplayName("Domain > Order > 할인 EventPolicy > 할인 Rule > 할인 조건 > 주중 이벤트 Condition")
class WeekdayEventConditionTest {

    @Mock
    EventCalendar eventCalendar;
    @Mock
    Order order;
    @InjectMocks
    WeekdayEventCondition weekdayEventCondition;

    @DisplayName("주중에 주문한 경우 참을 반환한다.")
    @Test
    void whenOrderedDateIsWeekdayThenReturnTrue(){
        //given
        BDDMockito.when(eventCalendar.isWeekday(any())).thenReturn(true);
        //when
        boolean result = weekdayEventCondition.isSatisfiedBy(order);
        //then
        assertTrue(result);
    }
}