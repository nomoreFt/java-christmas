package christmas.v0.restaurant;

import christmas.v0.event.EventPlanner;
import christmas.v0.order.EventExpectation;
import christmas.v0.order.OrderItem;

import java.time.LocalDate;
import java.util.List;

public class Restaurant {
    //적용되는 이벤트를 계산하는 책임
    //Event 정보에 대한 전문가에게 위임한다.
    private EventPlanner eventPlanner;
    private Menu menu;

    public EventExpectation calculateApplyRestaurantEvent(LocalDate reserveDate, List<OrderItem> items) {
        //EventPlanner에게 적용되는 이벤트를 계산하도록 위임한다.
        //OrderItem을 MenuItem으로 전환한다.
        List<OrderContextItem> orderFoodItems = convertToOrderContextItemsFrom(items);
        return eventPlanner.calculateApplyRestaurantEvent(OrderContext.of(reserveDate, orderFoodItems));
    }

    private List<OrderContextItem> convertToOrderContextItemsFrom(List<OrderItem> items) {
        List<OrderContextItem> orderFoodItems = items.stream()
                .map(orderItem -> {
                    MenuItem menuItem = menu.findMenuItem(orderItem.menuName());
                    return OrderContextItem.of(menuItem, orderItem.count());
                }).toList();
        return orderFoodItems;
    }
}
