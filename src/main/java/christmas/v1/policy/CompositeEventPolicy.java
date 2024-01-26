package christmas.v1.policy;

import christmas.v1.EventPolicy;
import christmas.v1.Order;

import java.util.LinkedHashMap;
import java.util.Map;

public final class CompositeEventPolicy implements EventPolicy {
    private final Map<EventPolicy, Integer> priorityPolicies = new LinkedHashMap<>();

    public void addPolicy(EventPolicy policy, int priority) {
        priorityPolicies.put(policy, priority);
    }
    @Override
    public void applyEvent(Order order) {
        priorityPolicies.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> entry.getKey().applyEvent(order));
    }
}
