package subway.ui.constants;

public enum ErrorMessages {
    INVALID("[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.\n"),
    DUPLICATE("[ERROR] 출발역과 도착역이 동일합니다.\n"),
    UNKNOWN("[ERROR] 예기치 못한 오류가 발생했습니다.\n");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
