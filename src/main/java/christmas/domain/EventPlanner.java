package christmas.domain;

import christmas.domain.event.condition.EventCondition;
import christmas.domain.event.policy.EventPolicy;

import java.util.Arrays;
import java.util.List;

public class EventPlanner {
    List<EventPolicy> eventPolicy;

    public EventPlanner(EventPolicy... eventPolicy) {
        this.eventPolicy = Arrays.asList(eventPolicy);
    }
    public Restaurant.ReservationConfirmation checkEvent(Reservation reservation) {
        eventPolicy
        //증정 확인
        return null;

    }
}
