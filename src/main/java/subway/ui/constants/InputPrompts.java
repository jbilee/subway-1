package subway.ui.constants;

public enum InputPrompts {
    OPTION_PROMPT("\n## 원하는 기능을 선택하세요."),
    DEPARTURE_PROMPT("\n## 출발역을 입력하세요."),
    DESTINATION_PROMPT("\n## 도착역을 입력하세요.");

    private final String text;

    InputPrompts(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
