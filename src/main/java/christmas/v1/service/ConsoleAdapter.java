package christmas.v1.service;

import christmas.v1.order.Order;
import christmas.v1.order.OrderItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleAdapter {
    private final Scanner scanner;
    private final OrderApplyEventService orderApplyEventService;
    private final MenuParser menuParser;
    public ConsoleAdapter(OrderApplyEventService orderApplyEventService, MenuParser menuParser) {
        this.menuParser = menuParser;
        this.scanner = new Scanner(System.in);
        this.orderApplyEventService = orderApplyEventService;
    }

    public void startInteraction() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");

        LocalDate visitDate = getVisitDate();
        Order order = createOrderFromInput(visitDate);
        OrderResult orderResult = orderApplyEventService.requestOrder(order);

        displayOrderResult(orderResult);
    }

    private LocalDate getVisitDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        int day = Integer.parseInt(scanner.nextLine());
        return LocalDate.of(2023, 12, day);
    }

    private Order createOrderFromInput(LocalDate visitDate) {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String menuInput = scanner.nextLine();

        List<OrderItem> parsedItems = menuParser.parseOrderItems(menuInput);
        return Order.of(visitDate, parsedItems);
    }


    private void displayOrderResult(OrderResult orderResult) {
        System.out.println("\n12월 " + orderResult.getOrderedDate() + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");

        System.out.println("<주문 메뉴>");
        orderResult.getOrderList().forEach(orderItem -> {
            System.out.println(orderItem.getMenu().getName() + " " + orderItem.getQuantity() + "개");
        });

        System.out.println("\n<할인 전 총주문 금액>\n" + orderResult.getOrder().getTotalBeforeDiscount() + "원");

        // 증정 메뉴와 혜택 내역 출력
        // ...

        System.out.println("<할인 후 예상 결제 금액>\n" + orderResult.getOrder().getFinalTotal() + "원");

        System.out.println("\n<12월 이벤트 배지>\n" + (orderResult.getOrder().getBadge() != null ? orderResult.getOrder().getBadge() : "없음"));
    }

    private double getMenuPriceByName(String name) {
        // 메뉴 이름에 따른 가격 반환 로직 구현 필요
        return 0.0; // 예시
    }
}