package subway.helpers;

import subway.ui.InputView;
import subway.ui.constants.ErrorMessages;
import subway.ui.constants.UiText;

public class InputController {
    private final InputView inputView;

    public InputController(InputView inputView) {
        this.inputView = inputView;
    }

    public String getMenuSelection() {
        System.out.println(UiText.MAIN_MENU_PROMPT.getText());
        while (true) {
            try {
                String response = inputView.readOption();
                this.validateOption(response);
                return response;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void validateOption(String option) {
        if (!option.equals("1") && !option.equals("Q")) {
            throw new IllegalArgumentException(ErrorMessages.INVALID.getMessage());
        }
    }
}
