package christmas;

import christmas.v1.Badge;
import christmas.v1.EventCalendar;
import christmas.v1.Gift;
import christmas.v1.calculator.ChristmasDdayDiscountCalculator;
import christmas.v1.calculator.SpecialDayDiscountCalculator;
import christmas.v1.calculator.WeekdayDiscountCalculator;
import christmas.v1.calculator.WeekendDiscountCalculator;
import christmas.v1.condition.*;
import christmas.v1.menu.MenuBoard;
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
        EventCalendar eventCalendar = EventCalendar.of(
                LocalDate.of(2023, 12, 1),
                LocalDate.of(2023, 12, 25)
        );


        /*
        EventPolicy 구성
         */
        CompositeEventPolicy compositeEventPolicy = new CompositeEventPolicy();

        /**
         * 할인 정책
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
                DiscountRule.of(specialDayEventCondition, specialDayDiscountCalculator, RuleDescription.SPECIALDAY_DISCOUNT),
                DiscountRule.of(weekdayEventCondition, weekdayDiscountCalculator, RuleDescription.WEEKDAY_DISCOUNT),
                DiscountRule.of(weekendEventCondition, weekendDiscountCalculator, RuleDescription.WEEKEND_DISCOUNT)
        );


        /**
         * 증정 정책
         */

        //GiftCondition
        ChampagneGiftCondition champagneGiftCondition = ChampagneGiftCondition.create();
        //GiftRule
        GiftRule.of(champagneGiftCondition, RuleDescription.CHAMPEIGN_GIFT, Gift.CHAMPAGNE);
        //GiftEventPolicy
        GiftEventPolicy giftEventPolicy = GiftEventPolicy.of(
                GiftRule.of(champagneGiftCondition, RuleDescription.CHAMPEIGN_GIFT, Gift.CHAMPAGNE)
        );


        /**
         * 배지 정책
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

        //CompositeEventPolicy
        compositeEventPolicy.addPolicy(discountEventPolicy, 0);
        compositeEventPolicy.addPolicy(giftEventPolicy, 1);
        compositeEventPolicy.addPolicy(badgeEventPolicy, 2);





        OrderApplyEventService orderApplyEventService = new OrderApplyEventService(compositeEventPolicy);

        //메뉴판 형성
        MenuBoard menuBoard = new MenuBoard();


        //메뉴리더서비스
        MenuReaderService menuReaderService = new MenuReaderService();

        //메뉴파서
        MenuParser menuParser = new MenuParser();


        //콘솔어댑터
        ConsoleAdapter consoleAdapter = new ConsoleAdapter();
        consoleAdapter.startInteraction();
    }
}
