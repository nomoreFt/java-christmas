package christmas.v0.restaurant;

import java.util.List;

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

