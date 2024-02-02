package christmas.v1.service;

import christmas.v1.menu.Menu;
import christmas.v1.menu.MenuBoard;

public class MenuReaderService {
    private final MenuBoard menuBoard;
    public MenuReaderService(MenuBoard menuBoard) {
        this.menuBoard = menuBoard;
    }
    public Menu findMenuByName(String menuName) {
        return menuBoard.findMenuByName(menuName);
    }
}
