package christmas.v1.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuBoard {
    private List<Menu> menus = new ArrayList<>();

    private MenuBoard(Menu... initialMenus) {
        addMenu(initialMenus);
    }
    public static MenuBoard createWith(Menu... initialMenus) {
        return new MenuBoard(initialMenus);
    }
    public void addMenu(Menu... menu) {
        menus.addAll(List.of(menu));
    }


    public Menu findMenuByName(String menuName) {
        return menus.stream()
                .filter(menu -> menu.isSameName(menuName))
                .findFirst()
                .orElse(null);
    }
}
