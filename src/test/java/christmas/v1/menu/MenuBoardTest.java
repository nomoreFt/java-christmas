package christmas.v1.menu;

import christmas.config.UnitTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Domain > Menu > MenuBoard")
@UnitTest
class MenuBoardTest {

    @DisplayName("메뉴판에 메뉴 이름으로 메뉴를 조회한다.")
    @Test
    void givenMenuNameWhenFindMenuThenReturnMenu() {
        // given
        String menuName = "피자";
        Menu salad = Menu.of("샐러드", 10_000, MenuType.APPETIZER);
        Menu expect = Menu.of(menuName, 15_000, MenuType.MAIN);
        Menu coke = Menu.of("닥터페퍼", 2_000, MenuType.DRINK);
        MenuBoard menuBoard = MenuBoard.createWith(
                salad
                ,expect
                ,coke
        );

        // when
        Menu result = menuBoard.findMenuByName(menuName);

        // then
        Assertions.assertEquals(expect, result);
    }

}