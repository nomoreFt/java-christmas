package christmas.v1.adapter.io.valid;

public record ValidationResult(
    boolean isValid,
    ValidationErrorMsg message,
    String additionalInfo
) {
    public static ValidationResult valid() {
        return new ValidationResult(true, ValidationErrorMsg.NONE, "");
    }


    public static ValidationResult invalid(ValidationErrorMsg message) {
        return new ValidationResult(false, message, "");
    }
    public static ValidationResult invalid(ValidationErrorMsg message, String additionalInfo) {
        return new ValidationResult(false, message, additionalInfo);
    }

    public String getErrorMessage() {
        return message.getMessage()+additionalInfo;
    }
}

