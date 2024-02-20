package christmas.v1.service;

import christmas.v1.common.Money;
import christmas.v1.menu.Menu;
import christmas.v1.menu.MenuBoard;
import christmas.v1.menu.MenuType;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;


@ExtendWith(MockitoExtension.class)
class MenuReaderServiceTest {
    @Mock private MenuBoard menuBoard;
    @InjectMocks private MenuReaderService menuReaderService;


    @Test
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