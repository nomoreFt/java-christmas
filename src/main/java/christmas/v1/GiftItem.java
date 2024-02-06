package christmas.v1;

public class GiftItem {
    private Gift gift;
    private int quantity;

    public GiftItem(Gift gift, int quantity) {
        this.gift = gift;
        this.quantity = quantity;
    }

    public Gift getGift() {
        return gift;
    }

    public int getQuantity() {
        return quantity;
    }

    //toString
    @Override
    public String toString() {
        return gift.getName() + " " + quantity + "개";
    }

    public Money getGiftPrice() {
        return gift.getPrice().multiply(quantity);
    }
}
