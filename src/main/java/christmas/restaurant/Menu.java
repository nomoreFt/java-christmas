package christmas.restaurant;

import christmas.order.OrderItem;

import java.util.List;
import java.util.stream.Collectors;

public class Menu {
    private List<MenuItem> menuItems;

    public boolean isAvailableMenu(String menuName) {
        return menuItems.stream()
                .anyMatch(menuItem -> menuItem.isSameFood(menuName));
    }

    public MenuItem findMenuItem(String menuName) {
        return menuItems.stream()
                .filter(menuItem -> menuItem.isSameFood(menuName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Menu item not found: " + menuName));
    }


}

