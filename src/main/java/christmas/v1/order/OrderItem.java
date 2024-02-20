package christmas.v1.order;

import christmas.v1.common.Money;
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
    public long getCount() {
        return count;
    }

    public String getName() {
        return menu.getName();
    }

    //menuType이 음료인지
    public boolean isBeverage() {
        return menu.isSameType(MenuType.DRINK);
    }
}
