package christmas.event;

import christmas.event.condition.EventCondition;
import christmas.order.EventExpectation;
import christmas.restaurant.OrderContext;

public abstract class EventPolicy {
    //Policy당 조건 1개임을 명시
    private EventCondition eventCondition;
    public boolean isApplicable(OrderContext order){
        return (eventCondition.isSatisfied(order));
    }



    abstract protected void applyTo(OrderContext orderContext, EventExpectation eventExpectation);
}
