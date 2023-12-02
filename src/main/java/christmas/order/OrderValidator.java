package christmas.order;

import christmas.restaurant.Menu;

import java.util.List;

public class OrderValidator {
    private Menu menu;
    public void validateDate(int reservationDate) {
        //checking beetween 1~31 reservationDate
        if (reservationDate < 1 || reservationDate > 31) {
            throw new IllegalArgumentException("Invalid reservation date: " + reservationDate);
        }
    }

    public void validateMenuItems(List<OrderItem> items) {
        //menu에서 menuItem을 찾아서 items에 있는 menuItem과 같은 음식이 있는지 체크한다.
        items.stream()
                .forEach(item -> {
                    if (!menu.isAvailableMenu(item.menuName())) {
                        throw new IllegalArgumentException("Menu item not found: " + item.menuName());
                    }
                });
    }
}
