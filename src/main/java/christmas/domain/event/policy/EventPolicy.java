package christmas.domain.event.policy;

import christmas.domain.EventResult;



public interface EventPolicy {
    EventResult apply(EventContext context);
}
