package subway.ui.constants;

public enum InputPrompts {
    OPTION_PROMPT("## 원하는 기능을 선택하세요."),
    DEPARTURE_PROMPT("## 출발역을 입력하세요."),
    DESTINATION_PROMPT("## 출발역을 입력하세요.");

    private final String text;

    InputPrompts(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
