package christmas.v1;

import christmas.v1.order.OrderItem;
import christmas.v1.service.MenuParser;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ConsoleInputEventHandler implements InputEventHandler{
    private final Scanner scanner;
    private final MenuParser menuParser;

    public ConsoleInputEventHandler(MenuParser menuParser) {
        scanner = new Scanner(System.in);
        this.menuParser = menuParser;
    }

    @Override
    public LocalDate getVisitDate() {
        System.out.println(Messages.get("visit.date.prompt"));
        int day = Integer.parseInt(scanner.nextLine());
        return LocalDate.of(2023, 12, day);
    }

    @Override
    public List<OrderItem> getOrderItems() {
        System.out.println(Messages.get("order.menus.prompt"));
        String menuInput = scanner.nextLine();
        return menuParser.parseOrderItems(menuInput);
    }
}
