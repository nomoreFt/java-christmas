package christmas.v1.adapter.io;

import christmas.v1.menu.Menu;
import christmas.v1.order.OrderItem;
import christmas.v1.service.MenuReaderService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuParser {
    private final MenuReaderService menuReaderService;
    private final Pattern menuEntryPattern = Pattern.compile("^(.+)\\-(\\d+)$");

    public MenuParser(MenuReaderService menuReaderService) {
        this.menuReaderService = menuReaderService;
    }

    public List<OrderItem> parseOrderItems(String menuInput) {
        List<OrderItem> orderItems = new ArrayList<>();
        String[] menuEntries = menuInput.split(",");

        for (String entry : menuEntries) {
            Matcher matcher = menuEntryPattern.matcher(entry.trim());
            String[] parts = entry.split("-");
            // 정규식을 통한 메뉴 입력값 검증
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Invalid menu entry: " + entry);
            }


            String menuName = matcher.group(1).trim();
            int quantity = Integer.parseInt(matcher.group(2).trim());
            // 메뉴 수량이 1보다 작으면 예외 발생
            if (quantity < 1) {
                throw new IllegalArgumentException("Menu quantity must be at least 1");
            }


            Menu menu = menuReaderService.findMenuByName(menuName);
            if (menu == null) {
                throw new IllegalArgumentException("Menu item not found: " + menuName);
            }

            orderItems.add(OrderItem.of(menu, quantity));
        }

        return orderItems;
    }
}
