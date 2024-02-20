package christmas.v1.adapter.io;

import camp.nextstep.edu.missionutils.Console;
import christmas.v1.adapter.InputEventHandler;
import christmas.v1.adapter.io.valid.OrderClientInputValidator;
import christmas.v1.adapter.io.valid.ValidationResult;
import christmas.v1.order.OrderItem;

import java.time.LocalDate;
import java.util.List;

public class ConsoleInputEventHandler implements InputEventHandler {

    private final OrderClientInputValidator orderClientInputValidator;
    private final MenuParser menuParser;

    public ConsoleInputEventHandler(OrderClientInputValidator orderClientInputValidator, MenuParser menuParser) {
        this.orderClientInputValidator = orderClientInputValidator;
        this.menuParser = menuParser;
    }

    @Override
    public LocalDate getVisitDate() {
        while (true) { // 유효한 입력을 받을 때까지 반복
            System.out.println(Messages.get("visit.date.prompt")); // 사용자에게 입력 요청

            String dayInput = Console.readLine(); // 사용자로부터 입력 받음

            // OrderValidator를 통한 유효성 검사
            ValidationResult validationResult = orderClientInputValidator.validateVisitDay(dayInput);

            if (validationResult.isValid()) { // 유효성 검사를 통과한 경우
                return LocalDate.of(2023, 12, Integer.parseInt(dayInput)); // 유효한 LocalDate 객체 생성 후 반환
            }

            System.out.println(validationResult.getErrorMessage()); // 에러 메시지 출력

        }
    }

    @Override
    public List<OrderItem> getOrderItems() {
        while (true) { // 유효한 입력을 받을 때까지 반복
            System.out.println(Messages.get("order.menus.prompt")); // 사용자에게 입력 요청
            String menuInput = Console.readLine(); // 사용자로부터 입력 받음
            // OrderValidator를 통한 유효성 검사
            ValidationResult validationResult = orderClientInputValidator.validateOrderItems(menuInput, menuParser);

            if (validationResult.isValid()) { // 유효성 검사를 통과한 경우
                return menuParser.parseOrderItems(menuInput); // 유효한 메뉴 목록 반환
            }

            // 유효성 검사를 통과하지 못한 경우
            System.out.println(validationResult.getErrorMessage()); // 에러 메시지 출력
        }
    }
}
