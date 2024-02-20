package christmas.v1.order;

import christmas.v1.common.Money;

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

    public Money getGiftPrice() {
        return gift.getPrice().multiply(quantity);
    }

    public String getName() {
        return gift.getName();
    }

    public boolean existsGift() {
        return gift != Gift.NONE;
    }
}
