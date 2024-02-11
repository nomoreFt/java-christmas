package christmas.v1.service;

import christmas.v1.Gift;
import christmas.v1.GiftItem;
import christmas.v1.InputEventHandler;
import christmas.v1.OutputEventHandelr;
import christmas.v1.order.Order;
import christmas.v1.order.OrderItem;
import christmas.v1.order.event.EventBenefit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ConsoleAdapter {
    private final InputEventHandler inputEventHandler;
    private final OutputEventHandelr outputEventHandelr;
    private final OrderApplyEventService orderApplyEventService;
    public ConsoleAdapter(OrderApplyEventService orderApplyEventService,
                          InputEventHandler inputEventHandler,
                          OutputEventHandelr outputEventHandelr) {

        this.inputEventHandler = inputEventHandler;
        this.outputEventHandelr = outputEventHandelr;
        this.orderApplyEventService = orderApplyEventService;
    }

    public void startInteraction() {
        outputEventHandelr.displayWelcomeMessage();
        LocalDate visitDate = inputEventHandler.getVisitDate();
        List<OrderItem> orderItems = inputEventHandler.getOrderItems();
        Order order = Order.of(visitDate, orderItems);
        OrderResult orderResult = orderApplyEventService.requestOrder(order);
        outputEventHandelr.displayOrderResult(orderResult);
    }

}