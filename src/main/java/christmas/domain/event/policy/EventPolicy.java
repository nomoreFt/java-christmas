package christmas.domain.event.policy;

import christmas.domain.Reservation;

public interface EventPolicy {

    <T> T apply(Reservation object);
}
