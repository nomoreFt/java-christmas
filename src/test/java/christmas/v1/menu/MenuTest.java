package christmas.v1.menu;

import christmas.config.UnitTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Domain > Menu")
@UnitTest
class MenuTest {
    @DisplayName("메뉴 타입이 같으면 true를 반환한다.")
    @Test
    void isSameType() {
        // given
        Menu appetizer = Menu.of("샐러드", 10_000, MenuType.APPETIZER);
        Menu anotherAppetizer = Menu.of("피자", 15_000, MenuType.DRINK);

        // when
        boolean result = appetizer.isSameType(MenuType.APPETIZER);
        boolean anotherResult = anotherAppetizer.isSameType(MenuType.DRINK);
        boolean falseResult = appetizer.isSameType(MenuType.DRINK);
        // then
        Assertions.assertTrue(result);
        Assertions.assertTrue(anotherResult);
        Assertions.assertFalse(falseResult);
    }


    @DisplayName("메뉴 이름이 같으면 true를 반환한다.")
    @Test
    void isSameName() {
        //given
        Menu salad = Menu.of("샐러드", 10_000, MenuType.APPETIZER);
        Menu anotherSalad = Menu.of("닥터페퍼", 15_000, MenuType.DRINK);


        //when
        boolean result = salad.isSameName("샐러드");
        boolean anotherResult = anotherSalad.isSameName("닥터페퍼");
        boolean falseResult = salad.isSameName("피자");

        //then
        Assertions.assertTrue(result);
        Assertions.assertTrue(anotherResult);
        Assertions.assertFalse(falseResult);

    }
}