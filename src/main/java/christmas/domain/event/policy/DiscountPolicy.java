package christmas.domain.event.policy;

import christmas.domain.Reservation;
import christmas.domain.event.common.Money;
import christmas.domain.event.condition.discount.DiscountEventCondition;
import org.assertj.core.groups.Tuple;

import java.math.BigDecimal;
import java.util.*;

public class DiscountPolicy implements EventPolicy{
    private Set<DiscountEventCondition> discountEventConditions = new LinkedHashSet<>();

    public DiscountPolicy(DiscountEventCondition... discountEventConditions) {
        this.discountEventConditions = new LinkedHashSet<>(Arrays.asList(discountEventConditions));
    }

    @Override
    public DiscountResult apply(Reservation reservation) {
        HashMap<DiscountEventCondition, Money> discountMap = new HashMap<>();
        for (DiscountEventCondition condition : discountEventConditions) {
            if (condition.isSatisfiedBy(reservation)) {
                Money discountMoney = calculateDiscount(reservation, condition);
                discountMap.put(condition, discountMoney);
            }
        }

        return new DiscountResult(discountMap);
    }

    private Money calculateDiscount(Reservation reservation, DiscountEventCondition condition) {
        // 할인 금액 계산 로직
        return condition.getDiscountMoney(reservation);
    }

}
