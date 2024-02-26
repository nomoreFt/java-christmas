package christmas.v1.adapter.io;

import christmas.config.UnitTest;
import christmas.v1.menu.Menu;
import christmas.v1.menu.MenuType;
import christmas.v1.order.OrderItem;
import christmas.v1.service.MenuReaderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.when;


@DisplayName("Adapter > IO > MenuParser")
@UnitTest
class MenuParserTest {

    @Mock
    MenuReaderService menuReaderService;
    @InjectMocks
    MenuParser menuParser;


    @Test
    @DisplayName("사용자에게 입력받은 주문내역을 OrderItem으로 파싱한다.")
    void givenMenuInput_whenParseOrderItems_thenReturnsOrderItems() {
        // given
        String menuInput = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        when(menuReaderService.findMenuByName("티본스테이크")).thenReturn(Menu.of("티본스테이크", 30_000, MenuType.MAIN));
        when(menuReaderService.findMenuByName("바비큐립")).thenReturn(Menu.of("바비큐립", 25_000, MenuType.MAIN));
        when(menuReaderService.findMenuByName("초코케이크")).thenReturn(Menu.of("초코케이크", 5_000, MenuType.DESSERT));
        when(menuReaderService.findMenuByName("제로콜라")).thenReturn(Menu.of("제로콜라", 2_000, MenuType.DRINK));

        // when
        List<OrderItem> orderItems = menuParser.parseOrderItems(menuInput);

        // then
        assertNotNull(orderItems);
        assertEquals(4, orderItems.size());


        assertTrue(orderItems.stream().anyMatch(item -> item.getName().equals("티본스테이크") && item.getCount() == 1));
        assertTrue(orderItems.stream().anyMatch(item -> item.getName().equals("바비큐립") && item.getCount() == 1));
        assertTrue(orderItems.stream().anyMatch(item -> item.getName().equals("초코케이크") && item.getCount() == 2));
        assertTrue(orderItems.stream().anyMatch(item -> item.getName().equals("제로콜라") && item.getCount() == 1));

    }

    @DisplayName("사용자에게 입력받은 주문내역이 잘못된 경우 예외를 던진다.")
    @ParameterizedTest(name = "[{index}] {arguments}")
    @MethodSource("provideWrongMenuInput")
    void givenWrongMenuInput_whenParsingMenu_thenThrowException(String wrongMenuInput, String expectedMessage) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> menuParser.parseOrderItems(wrongMenuInput));

        Assertions.assertTrue(exception.getMessage().contains("Invalid menu entry:"));
    }

    private static Stream<Arguments> provideWrongMenuInput(){
        return Stream.of(
                Arguments.of("메뉴명과 수량 사이에 -가 없는 경우", "티본스테이크1,바비큐립-1,초코케이크-2,제로콜라-1"),
                Arguments.of("수량이 없는 경우", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-"),
                Arguments.of("수량이 0인 경우", "티본스테이크-1,바비큐립-1,초코케이크-0,제로콜라-1")
        );
    }


    @DisplayName("MenuBoard에 없는 메뉴명이 주문내역에 포함된 경우 예외를 던진다.")
    @Test
    void givenNoneMenuInBoard_whenParsingMenu_thenThrowException() {
        // given
        String menuInput = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        when(menuReaderService.findMenuByName("티본스테이크")).thenReturn(Menu.of("티본스테이크", 30_000, MenuType.MAIN));
        when(menuReaderService.findMenuByName("바비큐립")).thenReturn(Menu.of("바비큐립", 25_000, MenuType.MAIN));
        when(menuReaderService.findMenuByName("초코케이크")).thenReturn(Menu.of("초코케이크", 5_000, MenuType.DESSERT));
        when(menuReaderService.findMenuByName("제로콜라")).thenReturn(null);

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> menuParser.parseOrderItems(menuInput));

        // then
        assertTrue(exception.getMessage().contains("Menu item not found: 제로콜라"));
    }

}