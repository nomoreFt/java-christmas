package christmas.v1.menu;

import java.util.List;

public class MenuBoard {
    private List<Menu> menus;

    public Menu findMenuByName(String menuName) {
        return menus.stream()
                .filter(menu -> menu.isSameName(menuName))
                .findFirst()
                .orElse(null);
    }
}
