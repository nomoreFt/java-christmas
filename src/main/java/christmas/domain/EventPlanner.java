package christmas.domain;


import christmas.domain.event.policy.*;
import christmas.dto.BadgeResult;
import christmas.dto.DiscountResult;
import christmas.dto.GiftResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class EventPlanner {
    private EventCalendar eventCalendar;
    private List<EventPolicy> eventPolicies = new ArrayList<>();

    public EventPlanner(EventCalendar eventCalendar, EventPolicy... eventPolicies) {
        this.eventCalendar = eventCalendar;
        this.eventPolicies = Arrays.asList(eventPolicies);
    }

    public ReservationConfirmation applyEvents(EventContext context) {
        for (EventPolicy policy : eventPolicies) {
            if (policy instanceof DiscountPolicy discountPolicy) {
                //어떤 Condition과 할인 금액 리스트가 들어있는 DiscountResult
                DiscountResult discountResult = (DiscountResult) discountPolicy.apply(context);
                context.setTotalDiscountAmount(discountResult.calculateAllDiscountMoney());
            } else if (policy instanceof GiftPolicy giftPolicy) {
                //어떤 Condition과 Gift가 들어있는지 GiftResult
                GiftResult apply = (GiftResult) giftPolicy.apply(context);
            }
        }
        for(EventPolicy policy : eventPolicies){
            if(policy instanceof BadgePolicy badgePolicy){
                if (context.isCalculatedDiscount()) {
                    //배지정보 추출
                BadgeResult apply = (BadgeResult) badgePolicy.apply(context);
                }
            }
        }

        return null;
    }
}
