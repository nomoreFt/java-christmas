package christmas.v1.order.policy.rule.condition;

import christmas.v1.common.Money;
import christmas.v1.order.Order;
import christmas.v1.order.policy.rule.EventCondition;

public class ChampagneGiftCondition implements EventCondition {
    //할인조건 12만원 금액표기
    private static final Money CHAMPAGNE_GIFT_LIMIT = Money.won(120_000);

    private ChampagneGiftCondition() {
    }

    public static ChampagneGiftCondition create() {
        return new ChampagneGiftCondition();
    }

    //할인 전 총주문 금액이 12만 원 이상인 경우 샴페인을 선물합니다.
    @Override
    public boolean isSatisfiedBy(Order order) {
        if (order.calculateBeforeDiscountAmount().isGreaterThanOrEqual(CHAMPAGNE_GIFT_LIMIT)) {
            return true;
        }
        return false;
    }
}
