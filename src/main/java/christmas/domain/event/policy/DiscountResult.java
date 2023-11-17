package christmas.domain.event.policy;

import christmas.domain.event.common.Money;
import christmas.domain.event.condition.discount.DiscountEventCondition;
import org.assertj.core.groups.Tuple;

import java.math.BigDecimal;
import java.util.*;

public class DiscountResult {

    private final Map<DiscountEventCondition, Money> discountMap;

    public DiscountResult(Map<DiscountEventCondition, Money> discountMap) {
        this.discountMap = Collections.unmodifiableMap(new HashMap<>(discountMap));
    }

    public Money calculateAllDiscountMoney(){
        return discountMap.values().stream()
                .reduce(Money.ZERO, Money::plus);
    }

}
