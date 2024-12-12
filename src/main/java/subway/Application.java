package subway;

import subway.domain.Handler;
import subway.helpers.InputController;
import subway.ui.InputView;

public class Application {
    public static void main(String[] args) {
        InputController inputController = new InputController(new InputView());

        String option = inputController.getOption();
        if (option.equals("Q")) {
            return;
        }
    }
}
