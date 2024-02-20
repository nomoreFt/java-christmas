package christmas.v1.order.policy.rule;

public enum RuleDescription {
    CHRISTMAS_DISCOUNT("크리스마스 디데이 할인"),
    SPECIALDAY_DISCOUNT("특별 할인"),
    WEEKDAY_DISCOUNT("평일 할인"),
    WEEKEND_DISCOUNT("주말 할인"),
    CHAMPEIGN_GIFT("증정 이벤트");

    private final String description;


    RuleDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
