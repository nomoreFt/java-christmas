package christmas.v1.service;

import christmas.config.UnitTest;
import christmas.v1.common.Money;
import christmas.v1.order.Badge;
import christmas.v1.order.EventPolicy;
import christmas.v1.order.EventValidator;
import christmas.v1.order.Order;
import christmas.v1.service.dto.OrderResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.when;

@UnitTest
@DisplayName("Service > OrderApplyEventService")
class OrderApplyEventServiceTest {

    @Mock private EventPolicy eventPolicy;
    @Mock private EventValidator eventValidator;
    @Mock private Order order;
    @InjectMocks private OrderApplyEventService orderApplyEventService;

    @BeforeEach
    void setUp() {
        // Order 객체의 동작 설정
        when(order.getOrderedDate()).thenReturn(LocalDate.now());
        when(order.getOrderItems()).thenReturn(Collections.emptyList());
        when(order.calculateBeforeDiscountAmount()).thenReturn(Money.won(10000));
        when(order.calculateAppliedGifts()).thenReturn(Collections.emptyList());
        when(order.calculateAppliedBenefits()).thenReturn(Collections.emptyList());
        when(order.calculateTotalBenefitAmount()).thenReturn(Money.won(0));
        when(order.calculateTotalAfterDiscount()).thenReturn(Money.won(10000));
        when(order.getAppliedBadge()).thenReturn(Badge.NONE);
    }

    @Test
    @DisplayName("requestOrder 메서드는 Order에 이벤트를 적용하고 OrderResult를 반환한다")
    void requestOrderShouldReturnCorrectOrderResult() {
        // given
        OrderResult expectedOrderResult = OrderResult.from(order);

        // when
        OrderResult actualOrderResult = orderApplyEventService.requestOrder(order);

        // then
        assertAll(
                () -> assertEquals(expectedOrderResult.orderedDate(), actualOrderResult.orderedDate()),
                () -> assertEquals(expectedOrderResult.orderList(), actualOrderResult.orderList()),
                () -> assertEquals(expectedOrderResult.totalBeforeDiscount(), actualOrderResult.totalBeforeDiscount()),
                () -> assertEquals(expectedOrderResult.gifts(), actualOrderResult.gifts()),
                () -> assertEquals(expectedOrderResult.benefits(), actualOrderResult.benefits()),
                () -> assertEquals(expectedOrderResult.totalBenefitAmount(), actualOrderResult.totalBenefitAmount()),
                () -> assertEquals(expectedOrderResult.totalAfterDiscount(), actualOrderResult.totalAfterDiscount()),
                () -> assertEquals(expectedOrderResult.badge(), actualOrderResult.badge())
        );
    }
}