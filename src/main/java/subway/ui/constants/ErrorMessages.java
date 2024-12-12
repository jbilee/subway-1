package subway.ui.constants;

public enum ErrorMessages {
    INVALID("[ERROR] 잘못된 입력입니다. 다시 입력해 주세요."),
    DUPLICATE("[ERROR] 출발역과 도착역이 동일합니다."),
    UNREACHABLE("[ERROR] 이동할 수 있는 노선이 없습니다.");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
