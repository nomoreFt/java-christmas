package christmas.domain.event.policy;


import christmas.domain.DiscountDetail;
import christmas.domain.EventCalendar;
import christmas.domain.EventResult;
import christmas.domain.Reservation;
import christmas.domain.event.common.Money;
import christmas.domain.event.condition.discount.DiscountEventCondition;
import christmas.dto.DiscountResult;

import java.util.ArrayList;
import java.util.List;

public class DiscountPolicy implements EventPolicy {
    private List<DiscountEventCondition> conditions;

    @Override
    public EventResult apply(EventContext context) {
        List<DiscountDetail> discountList = new ArrayList<>();
        // 할인 로직 구현
        for(DiscountEventCondition condition : conditions){
            if(condition.isSatisfiedBy(context)){
                //discountList.add(...); // 할인 금액 계산
                Money discountMoney = condition.getDiscountMoney(context);
                discountList.add(DiscountDetail.of(condition, discountMoney));
            }
        }
        return DiscountResult.of(discountList);
    }
}