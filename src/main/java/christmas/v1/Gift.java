package christmas.v1;

public enum Gift {
    NONE("없음",0)
    ,CHAMPAGNE("샴페인",25000);

    private final String name;
    private final Money price;

    Gift(String name, int price) {
        this.name = name;
        this.price = Money.won(price);
    }
    public String getName() {
        return name;
    }
    public Money getPrice() {
        return price;
    }
}
