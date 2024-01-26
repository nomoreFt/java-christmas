package christmas.v0.order;

public class OrderItem {
    private String menuName;
    private long count;

    public OrderItem(String menuName, long count) {
        this.menuName = menuName;
        this.count = count;
    }

    public String menuName() {
        return menuName;
    }

    public long count() {
        return count;
    }
}
