package christmas.v1.adapter;

import christmas.v1.order.OrderItem;
import christmas.v1.service.MenuParser;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderClientInputValidator {


    public ValidationResult validateVisitDay(String dayInput) {
        int reservationDate;
        try {
            reservationDate = Integer.parseInt(dayInput);
        } catch (NumberFormatException e) {
            return ValidationResult.invalid(ValidationErrorMsg.INVALID_DATE, "숫자만 입력해주세요.");
        }

        if (reservationDate < 1 || reservationDate > 31) {
            return ValidationResult.invalid(ValidationErrorMsg.INVALID_DATE, "1~31 사이의 숫자를 입력해주세요.");
        }

        return ValidationResult.valid();
    }

    public ValidationResult validateOrderItems(String menuInput, MenuParser menuParser) {
        Set<String> menuNames = new HashSet<>();
        List<OrderItem> orderItems;

        try {
            orderItems = menuParser.parseOrderItems(menuInput);
        } catch (IllegalArgumentException e) {
            return ValidationResult.invalid(ValidationErrorMsg.INVALID_ORDER, e.getMessage());
        }

        //주문한 메뉴가 다 음료인 경우 유효하지 않은 주문으로 처리
        if (orderItems.stream().allMatch(OrderItem::isBeverage)) {
            return ValidationResult.invalid(ValidationErrorMsg.INVALID_ORDER, "음료만 주문할 수 없습니다.");
        }

        //orderItem 리스트 count의 총 합이 20개를 넘어가면 유효하지 않은 주문으로 처리
        long sum = orderItems.stream()
                .mapToLong(OrderItem::getCount)
                .sum();
        if (sum > 20) {
            return ValidationResult.invalid(ValidationErrorMsg.INVALID_ORDER, "주문은 총 20개까지만 가능합니다.");
        }

        for(OrderItem orderItem : orderItems) {
            if (!menuNames.add(orderItem.getName())) {
                return ValidationResult.invalid(ValidationErrorMsg.INVALID_ORDER, "중복된 메뉴가 있습니다.");
            }
        }

        return ValidationResult.valid();
    }
}
