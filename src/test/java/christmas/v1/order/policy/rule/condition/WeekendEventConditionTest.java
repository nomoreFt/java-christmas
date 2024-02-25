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
@DisplayName("Domain > Order > 할인 EventPolicy > 할인 Rule > 할인 조건 > 주말 이벤트 Condition")
class WeekendEventConditionTest {

    @Mock
    EventCalendar eventCalendar;
    @Mock
    Order order;
    @InjectMocks
    WeekendEventCondition weekendEventCondition;

    @DisplayName("주말에 주문한 경우 참을 반환한다.")
    @Test
    void whenOrderedDateIsWeekendThenReturnTrue() {
        // given
        BDDMockito.when(eventCalendar.isWeekend(any())).thenReturn(true);
        // when
        boolean result = weekendEventCondition.isSatisfiedBy(order);
        // then
        assertTrue(result);
    }

}