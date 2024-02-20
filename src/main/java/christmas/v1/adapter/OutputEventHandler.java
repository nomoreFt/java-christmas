package christmas.v1.adapter;

import christmas.v1.service.dto.OrderResult;

public interface OutputEventHandler {
    void displayWelcomeMessage();
    void displayOrderResult(OrderResult orderResult);
}
