package christmas.dto;

import christmas.domain.DiscountDetail;
import christmas.domain.EventResult;
import christmas.domain.event.common.Money;
import java.util.*;

public class DiscountResult extends EventResult {

    private final List<DiscountDetail> appliedDiscountEventList;

    private DiscountResult(List<DiscountDetail> discountList) {
        this.appliedDiscountEventList = Collections.unmodifiableList(discountList);
    }

    public static DiscountResult of(List<DiscountDetail> discountList) {
        return new DiscountResult(discountList);
    }

    public Money calculateAllDiscountMoney() {
        return appliedDiscountEventList.stream()
                .map(DiscountDetail::getDiscountAmount)
                .reduce(Money.ZERO, Money::plus);
    }

}
