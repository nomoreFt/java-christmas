package christmas.v0.event;

import christmas.v0.order.EventExpectation;
import christmas.v0.restaurant.OrderContext;

public abstract class EventPolicy {
    //Policy당 조건 1개임을 명시
    private EventCondition eventCondition;
    public boolean isApplicable(OrderContext order){
        return (eventCondition.isSatisfied(order));
    }

    abstract protected void applyTo(OrderContext orderContext, EventExpectation eventExpectation);
}
