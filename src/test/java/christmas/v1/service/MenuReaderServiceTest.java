package christmas.v1.service;

import christmas.config.UnitTest;
import christmas.v1.common.Money;
import christmas.v1.menu.Menu;
import christmas.v1.menu.MenuBoard;
import christmas.v1.menu.MenuType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.when;


@UnitTest
@DisplayName("Service > MenuReaderService")
class MenuReaderServiceTest {
    @Mock private MenuBoard menuBoard;
    @InjectMocks private MenuReaderService menuReaderService;


    @Test
    @DisplayName("메뉴 이름으로 메뉴를 찾는다.")
    void findMenuByName() {
        // given
        String menuName = "Espresso";
        int price = 3000;
        MenuType menuType = MenuType.DRINK;
        Menu expectedMenu = Menu.of(menuName, price,menuType);
        when(menuBoard.findMenuByName(menuName)).thenReturn(expectedMenu);

        // when
        Menu resultMenu = menuReaderService.findMenuByName(menuName);

        // then
        assertEquals(expectedMenu, resultMenu);
        Assertions.assertTrue(resultMenu.isSameName(menuName));
        Assertions.assertTrue(resultMenu.isSameType(menuType));
        Assertions.assertEquals(Money.won(price), resultMenu.getPrice());
    }

}