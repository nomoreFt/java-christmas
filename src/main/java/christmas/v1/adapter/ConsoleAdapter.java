package christmas.v1.adapter;


import christmas.v1.order.Order;
import christmas.v1.order.OrderItem;
import christmas.v1.service.OrderApplyEventService;
import christmas.v1.service.dto.OrderResult;

import java.time.LocalDate;
import java.util.List;


public class ConsoleAdapter {
    private final InputEventHandler inputEventHandler;
    private final OutputEventHandler outputEventHandler;
    private final OrderApplyEventService orderApplyEventService;
    public ConsoleAdapter(OrderApplyEventService orderApplyEventService,
                          InputEventHandler inputEventHandler,
                          OutputEventHandler outputEventHandler) {

        this.inputEventHandler = inputEventHandler;
        this.outputEventHandler = outputEventHandler;
        this.orderApplyEventService = orderApplyEventService;
    }

    public void startInteraction() {
        outputEventHandler.displayWelcomeMessage();
        LocalDate visitDate = inputEventHandler.getVisitDate();
        List<OrderItem> orderItems = inputEventHandler.getOrderItems();
        Order order = Order.of(visitDate, orderItems);
        OrderResult orderResult = orderApplyEventService.requestOrder(order);
        outputEventHandler.displayOrderResult(orderResult);
    }

}