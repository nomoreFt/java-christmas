package christmas.dto;

import christmas.domain.EventResult;
import christmas.domain.event.common.Badge;

public class BadgeResult extends EventResult {
    private Badge badge;

    public BadgeResult(Badge badge) {
        this.badge = badge;
    }

    public Badge getBadge() {
        return badge;
    }
}
