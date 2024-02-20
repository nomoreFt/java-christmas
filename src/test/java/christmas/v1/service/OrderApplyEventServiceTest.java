package christmas.v1.service;

import christmas.v1.common.Money;
import christmas.v1.order.Badge;
import christmas.v1.order.EventPolicy;
import christmas.v1.order.EventValidator;
import christmas.v1.order.Order;
import christmas.v1.service.dto.OrderResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
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