package christmas.domain;


import christmas.domain.event.policy.DiscountPolicy;
import christmas.domain.event.policy.DiscountResult;
import christmas.domain.event.policy.GiftPolicy;
import christmas.domain.event.policy.GiftResult;


public class EventPlanner {
    private DiscountPolicy discountPolicy;
    private GiftPolicy giftPolicy;

    public EventPlanner(DiscountPolicy discountPolicy, GiftPolicy giftPolicy) {
        this.discountPolicy = discountPolicy;
        this.giftPolicy = giftPolicy;
    }

    public Restaurant.ReservationConfirmation expectEvent(Reservation reservation) {
        //예약에 대해 각각 할인 정책을 적용하고, 결과값을 받음
        //할인정책결과 - 충족된 할인 컨디션과 할인 금액
        //사은품정책결과 - 충족된 사은품 컨디션과 사은품
        DiscountResult apply = discountPolicy.apply(reservation);
        GiftResult apply2 = giftPolicy.apply(reservation);
        //ReservationConfirmation 생성
        //
        return null;
    }
}
