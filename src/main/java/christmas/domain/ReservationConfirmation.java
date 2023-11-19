package christmas.domain;

import christmas.domain.event.common.Badge;
import christmas.domain.event.common.Gift;
import christmas.domain.event.common.Money;
import christmas.dto.DiscountResult;
import christmas.dto.GiftResult;

import java.util.List;

public class ReservationConfirmation {
    //Reservation-ReservationMenus에서 꺼내 주문 메뉴 출력
    private Reservation reservation;
    //Restaurant-Menu에서 꺼내 금액 계산
    private Money originalTotalAmount;
    //증정 메뉴 출력
    private GiftResult giftedItems;
    //DiscountDetail에서 혜택 내역 - 혜택 금액 각각 출력
    private DiscountResult discountDetails;
    //DiscountDetail에서 총 혜택 금액 출력
    private Money totalDiscountAmount;
    //최종 결제 금액 출력
    private Money finalAmount;
    //Badge에서 증정 뱃지 출력
    private Badge awardedBadge;


    public ReservationMenu getReservationMenu(){
        return null;
    }
}
