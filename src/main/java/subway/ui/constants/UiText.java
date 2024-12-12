package subway.ui.constants;

public enum UiText {
    MAIN_MENU_PROMPT("## 메인 화면\n1. 경로 조회\nQ. 종료"),
    OPTIONS_MENU_PROMPT("\n## 경로 기준\n1. 최단 거리\n2. 최소 시간\nB. 돌아가기"),
    RESULT_HEADER("\n## 조회 결과\n"),
    DIVIDER("[INFO] ---\n"),
    INFO_PREFIX("[INFO] "),;

    private final String text;

    UiText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
