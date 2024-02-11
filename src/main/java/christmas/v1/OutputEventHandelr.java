package christmas.v1;

import christmas.v1.service.OrderResult;

public interface OutputEventHandelr {
    void displayWelcomeMessage();
    void displayOrderResult(OrderResult orderResult);
}
