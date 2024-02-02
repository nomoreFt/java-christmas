package christmas.v1.order;

import christmas.v1.Money;
import christmas.v1.menu.Menu;
import christmas.v1.menu.MenuType;

public class OrderItem {
    private Menu menu;
    private long count;

    protected OrderItem() {}
    private OrderItem(Menu menu, long count) {
        this.menu = menu;
        this.count = count;
    }
    //of
    public static OrderItem of(Menu menu, long count) {
        return new OrderItem(menu, count);
    }

    public boolean isSameType(MenuType menuType) {
        return menu.isSameType(menuType);
    }

    public Money getPrice() {
        return menu.getPrice().multiply(count);
    }

    @Override
    public String toString() {
        return menu.getName() + " " + count + "개";
    }
}
