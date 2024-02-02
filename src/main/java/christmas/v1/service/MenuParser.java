package christmas.v1.service;

import christmas.v1.menu.Menu;
import christmas.v1.order.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class MenuParser {
    private final MenuReaderService menuReaderService;

    public MenuParser(MenuReaderService menuReaderService) {
        this.menuReaderService = menuReaderService;
    }

    public List<OrderItem> parseOrderItems(String menuInput) {
        List<OrderItem> orderItems = new ArrayList<>();
        String[] menuEntries = menuInput.split(",");

        for (String entry : menuEntries) {
            String[] parts = entry.split("-");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid menu entry format");
            }

            String menuName = parts[0].trim();
            int quantity = Integer.parseInt(parts[1].trim());

            Menu menu = menuReaderService.findMenuByName(menuName);
            if (menu == null) {
                throw new IllegalArgumentException("Menu item not found: " + menuName);
            }

            OrderItem orderItem = OrderItem.of(menu, quantity);
            orderItems.add(orderItem);
        }

        return orderItems;
    }
}
