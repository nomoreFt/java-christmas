package christmas.v1.adapter.io.valid;

public enum ValidationErrorMsg {
    NONE(""),
    INVALID_DATE("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private final String message;

    ValidationErrorMsg(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}
