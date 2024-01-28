package christmas.v1.order;

import christmas.v1.Money;
import christmas.v1.menu.Menu;
import christmas.v1.menu.MenuType;

public class OrderItem {
    private Menu menu;
    private long count;

    public boolean isSameType(MenuType menuType) {
        return menu.isSameType(menuType);
    }

    public Money getPrice() {
        return menu.getPrice().multiply(count);
    }
}
