package christmas.event;

import christmas.order.EventExpectation;
import christmas.restaurant.OrderContext;

import java.util.List;

public class EventPlanner {
    //적용되는 이벤트를 계산하는 책임
    private List<EventPolicy> eventPolicies;

    public EventExpectation calculateApplyRestaurantEvent(OrderContext orderContext) {
        //EventPolicy에게 적용되는 이벤트를 계산하도록 위임한다.
        EventExpectation eventExpectation = EventExpectation.createEmpty();
        eventPolicies.stream()
                .forEach(eventPolicy -> {
                    if (eventPolicy.isApplicable(orderContext)) {
                        eventPolicy.applyTo(orderContext,eventExpectation);
                    }
                });

        return eventExpectation;
    }
}
