package christmas.domain.event.condition.badge;

import christmas.domain.EventCalendar;
import christmas.domain.event.common.Badge;
import christmas.domain.event.common.Money;

public interface BadgeEventCondition {
    boolean isSatisfiedBy(Money orderAmount);
    Badge getBadge(Money orderAmount);
}
