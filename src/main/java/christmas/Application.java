package christmas;

import christmas.v1.*;
import christmas.v1.calculator.ChristmasDdayDiscountCalculator;
import christmas.v1.calculator.SpecialDayDiscountCalculator;
import christmas.v1.calculator.WeekdayDiscountCalculator;
import christmas.v1.calculator.WeekendDiscountCalculator;
import christmas.v1.condition.*;
import christmas.v1.menu.Menu;
import christmas.v1.menu.MenuBoard;
import christmas.v1.menu.MenuType;
import christmas.v1.policy.BadgeEventPolicy;
import christmas.v1.policy.CompositeEventPolicy;
import christmas.v1.policy.DiscountEventPolicy;
import christmas.v1.policy.GiftEventPolicy;
import christmas.v1.rule.BadgeRule;
import christmas.v1.rule.DiscountRule;
import christmas.v1.rule.GiftRule;
import christmas.v1.rule.RuleDescription;
import christmas.v1.service.ConsoleAdapter;
import christmas.v1.service.MenuParser;
import christmas.v1.service.MenuReaderService;
import christmas.v1.service.OrderApplyEventService;

import java.time.LocalDate;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        /**
         * 인공물 구성
         */

        EventCalendar eventCalendar = EventCalendar.of(
                LocalDate.of(2023, 12, 1),
                LocalDate.of(2023, 12, 25)
        );

        /**
         * 도메인 이벤트 정책 구성
         */

        /*
        EventPolicy 구성 - Composite Pattern
         */
        CompositeEventPolicy compositeEventPolicy = new CompositeEventPolicy();

        /*
         * 할인 정책 - Leaf
         */
        //DiscountCondition
        ChristmasDdayEventCondition christmasDdayEventCondition = ChristmasDdayEventCondition.create(eventCalendar);
        SpecialDayEventCondition specialDayEventCondition = SpecialDayEventCondition.create(eventCalendar);
        WeekdayEventCondition weekdayEventCondition = WeekdayEventCondition.create(eventCalendar);
        WeekendEventCondition weekendEventCondition = WeekendEventCondition.create(eventCalendar);

        //DiscountCalculator
        ChristmasDdayDiscountCalculator christmasDdayDiscountCalculator = ChristmasDdayDiscountCalculator.create(eventCalendar);
        SpecialDayDiscountCalculator specialDayDiscountCalculator = SpecialDayDiscountCalculator.create();
        WeekdayDiscountCalculator weekdayDiscountCalculator = WeekdayDiscountCalculator.create();
        WeekendDiscountCalculator weekendDiscountCalculator = WeekendDiscountCalculator.create();

        DiscountEventPolicy discountEventPolicy = DiscountEventPolicy.of(
                DiscountRule.of(christmasDdayEventCondition, christmasDdayDiscountCalculator, RuleDescription.CHRISTMAS_DISCOUNT),
                DiscountRule.of(weekdayEventCondition, weekdayDiscountCalculator, RuleDescription.WEEKDAY_DISCOUNT),
                DiscountRule.of(weekendEventCondition, weekendDiscountCalculator, RuleDescription.WEEKEND_DISCOUNT),
                DiscountRule.of(specialDayEventCondition, specialDayDiscountCalculator, RuleDescription.SPECIALDAY_DISCOUNT)
        );


        /*
         * 증정 정책 - Leaf
         */

        //GiftCondition
        ChampagneGiftCondition champagneGiftCondition = ChampagneGiftCondition.create();
        //GiftRule
        GiftRule.of(champagneGiftCondition, RuleDescription.CHAMPEIGN_GIFT, Gift.CHAMPAGNE);
        //GiftEventPolicy
        GiftEventPolicy giftEventPolicy = GiftEventPolicy.of(
                GiftRule.of(champagneGiftCondition, RuleDescription.CHAMPEIGN_GIFT, Gift.CHAMPAGNE)
        );


        /*
         * 배지 정책 - Leaf
         */

        //BadgeCondition
        TreeBadgeCondition treeBadgeCondition = TreeBadgeCondition.create();
        SantaBadgeCondition santaBadgeCondition = SantaBadgeCondition.create();
        StarBadgeCondition starBadgeCondition = StarBadgeCondition.create();

        //BadgeEventPolicy
        BadgeEventPolicy badgeEventPolicy = BadgeEventPolicy.of(
                BadgeRule.of(starBadgeCondition, Badge.STAR),
                BadgeRule.of(treeBadgeCondition, Badge.TREE),
                BadgeRule.of(santaBadgeCondition, Badge.SANTA)
        );

        //CompositeEventPolicy - Composite
        compositeEventPolicy.addPolicy(discountEventPolicy, 0);
        compositeEventPolicy.addPolicy(giftEventPolicy, 1);
        compositeEventPolicy.addPolicy(badgeEventPolicy, 2);


        //메뉴판 형성
        MenuBoard menuBoard = MenuBoard.createWith(
                Menu.of("양송이스프", 6_000, MenuType.APPETIZER),
                Menu.of("타파스", 5_500, MenuType.APPETIZER),
                Menu.of("시저샐러드", 8_000, MenuType.APPETIZER),

                Menu.of("티본스테이크", 55_000, MenuType.MAIN),
                Menu.of("바비큐립", 54_000, MenuType.MAIN),
                Menu.of("해산물파스타", 35_000, MenuType.MAIN),
                Menu.of("크리스마스파스타", 25_000, MenuType.MAIN),

                Menu.of("초코케이크", 15_000, MenuType.DESSERT),
                Menu.of("아이스크림", 5_000, MenuType.DESSERT),

                Menu.of("제로콜라", 3_000, MenuType.DRINK),
                Menu.of("레드와인", 60_000, MenuType.DRINK),
                Menu.of("샴페인", 25_000, MenuType.DRINK)
        );

        /**
         * 서비스 구성
         */

        //주문 이벤트 적용 서비스
        OrderApplyEventService orderApplyEventService = new OrderApplyEventService(compositeEventPolicy);
        //메뉴 리더 서비스
        MenuReaderService menuReaderService = new MenuReaderService(menuBoard);

        /**
         * 어댑터 구성
         */

        //메뉴파서 - Adapter - Service 사이 계층
        MenuParser menuParser = new MenuParser(menuReaderService);

        ConsoleInputEventHandler consoleInputEventHandler = new ConsoleInputEventHandler(menuParser);
        ConsoleOutputEventHandler consoleOutputEventHandler = new ConsoleOutputEventHandler();
        //콘솔어댑터
        ConsoleAdapter consoleAdapter = new ConsoleAdapter(orderApplyEventService,
                consoleInputEventHandler,
                consoleOutputEventHandler);
        consoleAdapter.startInteraction();
    }
}
