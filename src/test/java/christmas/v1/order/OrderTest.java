package christmas.v1.order;

import christmas.config.UnitTest;
import christmas.v1.common.Money;
import christmas.v1.menu.Menu;
import christmas.v1.menu.MenuType;
import christmas.v1.order.benefit.DiscountBenefit;
import christmas.v1.order.benefit.GiftBenefit;
import christmas.v1.order.policy.rule.RuleDescription;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.*;


@UnitTest
@DisplayName("Domain > Order")
class OrderTest {
    EventPolicy policy = mock(EventPolicy.class);
    EventValidator validator = mock(EventValidator.class);
    Order order;

    @BeforeEach
    void setUp() {
        //Menu, OrderItem 생성
        List<OrderItem> orderItems = List.of(
                OrderItem.of(Menu.of("아메리카노", 3000, MenuType.DRINK), 2),
                OrderItem.of(Menu.of("카페라떼", 4000, MenuType.DRINK), 1),
                OrderItem.of(Menu.of("스테이크", 5000, MenuType.MAIN), 3),
                OrderItem.of(Menu.of("케이크", 6000, MenuType.DESSERT), 2),
                OrderItem.of(Menu.of("샐러드", 2000, MenuType.APPETIZER), 4)
        );

        order = Order.of(LocalDate.now(), orderItems);
    }


    @Test
    @DisplayName("주문 항목의 총 금액을 할인 전에 올바르게 계산한다")
    void calculateBeforeDiscountAmountCorrectly() {
        //given
        Money expected = Money.won(3000 * 2 + 4000 * 1 + 5000 * 3 + 6000 * 2 + 2000 * 4);

        //when
        Money result = order.calculateBeforeDiscountAmount();

        //then
        Assertions.assertEquals(expected, result, "할인 전 주문 항목의 총 금액이 올바르게 계산되어야 합니다.");
    }

    List<OrderItem> orderItems = List.of(
            OrderItem.of(Menu.of("아메리카노", 3000, MenuType.DRINK), 2),
            OrderItem.of(Menu.of("카페라떼", 4000, MenuType.DRINK), 1),
            OrderItem.of(Menu.of("스테이크", 5000, MenuType.MAIN), 3),
            OrderItem.of(Menu.of("케이크", 6000, MenuType.DESSERT), 2),
            OrderItem.of(Menu.of("샐러드", 2000, MenuType.APPETIZER), 4)
    );
    @Test
    @DisplayName("특정 메뉴 타입에 해당하는 주문 항목의 개수를 올바르게 계산한다")
    void countOrderItemByMenuTypeCorrectly() {
        //given
        MenuType drink = MenuType.DRINK;
        MenuType dessert = MenuType.DESSERT;
        MenuType main = MenuType.MAIN;
        MenuType appetizer = MenuType.APPETIZER;

        long drinkExpect = 3;
        long mainExpect = 3;
        long dessertExpect = 2;
        long appetizerExpect = 4;
        // 테스트 실행 및 결과 검증
        Assertions.assertEquals(drinkExpect, order.countOrderItemBy(drink), "음료의 총 금액이 올바르게 계산되어야 합니다.");
        Assertions.assertEquals(mainExpect, order.countOrderItemBy(main), "메인의 총 금액이 올바르게 계산되어야 합니다.");
        Assertions.assertEquals(dessertExpect, order.countOrderItemBy(dessert), "디저트의 총 금액이 올바르게 계산되어야 합니다.");
        Assertions.assertEquals(appetizerExpect, order.countOrderItemBy(appetizer), "전채의 총 금액이 올바르게 계산되어야 합니다.");
    }



    @Test
    @DisplayName("NONE이 아닌 GIFT만 올바르게 계산한다")
    void calculateAppliedGiftsCorrectly() {
        //given
        order.addGift(Gift.CHAMPAGNE);
        order.addGift(Gift.NONE);
        order.addGift(Gift.NONE);

        //when
        List<GiftItem> giftItems = order.calculateAppliedGifts();

        //then
        Assertions.assertEquals(1, giftItems.size(), "적용된 선물의 개수가 올바르게 계산되어야 합니다.");
        giftItems.forEach(giftItem -> {
            Assertions.assertEquals(Gift.CHAMPAGNE, giftItem.getGift(), "적용된 선물이 올바르게 계산되어야 합니다.");
            Assertions.assertEquals(1, giftItem.getQuantity(), "적용된 선물의 개수가 올바르게 계산되어야 합니다.");
        });

    }

    @Test
    @DisplayName("적용된 혜택들을 올바르게 계산한다")
    void calculateAppliedBenefitsCorrectly() {
        //given
        EventBenefit eventBenefit = DiscountBenefit.createWith(RuleDescription.CHRISTMAS_DISCOUNT,Money.won(1000));
        GiftBenefit giftBenefit = GiftBenefit.createWith(RuleDescription.CHAMPEIGN_GIFT, Gift.CHAMPAGNE);
        order.addBenefit(eventBenefit);
        order.addBenefit(giftBenefit);

        //when
        List<EventBenefit> benefits = order.calculateAppliedBenefits();

        //then
        Assertions.assertEquals(2, benefits.size(), "적용된 혜택의 개수가 올바르게 계산되어야 합니다.");
        benefits.forEach(benefit -> {
            if(benefit instanceof DiscountBenefit){
                Assertions.assertEquals(eventBenefit, benefit, "적용된 할인 혜택이 올바르게 계산되어야 합니다.");
            }else if(benefit instanceof GiftBenefit){
                Assertions.assertEquals(giftBenefit, benefit, "적용된 선물 혜택이 올바르게 계산되어야 합니다.");
            }
        });
    }

    @Test
    @DisplayName("주문에 뱃지를 올바르게 할당한다")
    void assignBadgeToOrder() {
        //given
        Badge expected = Badge.STAR;
        order.assignBadge(expected);

        //when
        Badge result = order.getAppliedBadge();

        //then
        Assertions.assertEquals(expected, result, "주문에 뱃지가 올바르게 할당되어야 합니다.");
    }

    @Test
    @DisplayName("뱃지는 가장 높은 등급의 뱃지가 할당된다")
    void assignBadgeToOrderHighest() {
        //given
        Badge expected = Badge.TREE;
        order.assignBadge(expected);
        Badge result1 = order.getAppliedBadge();

        Badge lower = Badge.STAR;
        order.assignBadge(lower);
        Badge result2 = order.getAppliedBadge();

        Badge highest = Badge.SANTA;
        order.assignBadge(highest);
        Badge result3 = order.getAppliedBadge();

        //then
        Assertions.assertEquals(expected, result1, "주문에 뱃지가 올바르게 할당되어야 합니다.");
        Assertions.assertEquals(expected, result2, "주문에 뱃지가 올바르게 할당되어야 합니다.");
        Assertions.assertEquals(highest, result3, "주문에 뱃지가 올바르게 할당되어야 합니다.");

    }

    @Test
    @DisplayName("총 혜택 금액을 올바르게 계산한다")
    void calculateTotalBenefitAmountCorrectly() {
        //given
        EventBenefit eventBenefit = DiscountBenefit.createWith(RuleDescription.CHRISTMAS_DISCOUNT,Money.won(4000));
        EventBenefit eventBenefit2 = DiscountBenefit.createWith(RuleDescription.SPECIALDAY_DISCOUNT,Money.won(1000));
        GiftBenefit giftBenefit = GiftBenefit.createWith(RuleDescription.CHAMPEIGN_GIFT, Gift.CHAMPAGNE);
        order.addBenefit(eventBenefit);
        order.addBenefit(eventBenefit2);
        order.addBenefit(giftBenefit);

        //when
        Money result = order.calculateTotalBenefitAmount();

        //then
        Money expected = Money.won(4000 + 1000).add(Gift.CHAMPAGNE.getPrice());

        Assertions.assertEquals(expected, result, "총 혜택 금액이 올바르게 계산되어야 합니다.");

    }

    @Test
    @DisplayName("할인 후 총 금액을 올바르게 계산한다")
    void calculateTotalAfterDiscountCorrectly() {
        //given
        EventBenefit eventBenefit = DiscountBenefit.createWith(RuleDescription.CHRISTMAS_DISCOUNT,Money.won(4000));
        EventBenefit eventBenefit2 = DiscountBenefit.createWith(RuleDescription.SPECIALDAY_DISCOUNT,Money.won(1000));
        GiftBenefit giftBenefit = GiftBenefit.createWith(RuleDescription.CHAMPEIGN_GIFT, Gift.CHAMPAGNE);
        order.addBenefit(eventBenefit);
        order.addBenefit(eventBenefit2);
        order.addBenefit(giftBenefit);
        order.addTotalDiscountAmount(Money.won(5000));


        //when

        Money expect = order.calculateBeforeDiscountAmount().minus(Money.won(5000));
        Money result = order.calculateTotalAfterDiscount();

        //then
        Assertions.assertEquals(expect, result, "할인 후 총 금액이 올바르게 계산되어야 합니다.");



    }



    @Test
    @DisplayName("이벤트 적용 조건을 충족시키면 정책을 적용한다")
    void testApplyEventWhenEligible() {

        // 이벤트 적용 조건을 충족시킴
        when(validator.isEligibleForEvent(any(Order.class))).thenReturn(true);

        Order order = Order.of(LocalDate.now(), Collections.emptyList());
        order.applyEvent(policy, validator);

        // 정책 적용 확인
        verify(policy).applyEvent(order);
    }

    @Test
    @DisplayName("이벤트 적용 조건을 충족시키지 않으면 정책을 적용하지 않는다")
    void testApplyEventWhenNotEligible() {
        EventPolicy policy = mock(EventPolicy.class);
        EventValidator validator = mock(EventValidator.class);

        // 이벤트 적용 조건을 충족시키지 않음
        when(validator.isEligibleForEvent(any(Order.class))).thenReturn(false);

        Order order = Order.of(LocalDate.now(), Collections.emptyList());
        order.applyEvent(policy, validator);

        // 정책이 적용되지 않았는지 확인
        verify(policy, never()).applyEvent(order);
    }

}