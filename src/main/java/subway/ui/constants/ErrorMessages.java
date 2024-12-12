package subway.ui.constants;

public enum ErrorMessages {
    INVALID("[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
