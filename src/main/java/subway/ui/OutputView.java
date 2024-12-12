package subway.ui;

import subway.ui.constants.UiText;

import java.util.List;

public class OutputView {
    public void printResults(List<String> path, double km, double m) {
        System.out.print(UiText.RESULT_HEADER.getText());
        System.out.print(UiText.DIVIDER.getText());
        System.out.println(UiText.INFO_PREFIX.getText() + "총 거리: " + km + "km");
        System.out.println(UiText.INFO_PREFIX.getText() + "총 소요 시간: " + (int)m + "분");
        System.out.print(UiText.DIVIDER.getText());
        for (String station : path) {
            System.out.print(UiText.INFO_PREFIX.getText() + station + "\n");
        }
    }
}
