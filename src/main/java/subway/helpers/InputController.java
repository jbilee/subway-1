package subway.helpers;

import subway.domain.constants.StationData;
import subway.ui.InputView;
import subway.ui.constants.ErrorMessages;
import subway.ui.constants.UiText;

public class InputController {
    private final InputView inputView;

    public InputController(InputView inputView) {
        this.inputView = inputView;
    }

    public String getOption() {
        String response = "";
        while (true) {
            try {
                response = this.getFirstMenu();
                if (response.equals("Q")) {
                    break;
                }
                response = this.getSecondMenu();
                if (response.equals("B")) {
                    continue;
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }
        return response;
    }

    public String getFirstMenu() {
        System.out.println(UiText.MAIN_MENU_PROMPT.getText());
        String response = this.inputView.readOption();
        this.validateFirstOption(response);
        return response;
    }

    public String getSecondMenu() {
        System.out.println(UiText.OPTIONS_MENU_PROMPT.getText());
        String response = this.inputView.readOption();
        this.validateSecondOption(response);
        return response;
    }

    private void validateFirstOption(String option) {
        if (!option.equals("1") && !option.equals("Q")) {
            throw new IllegalArgumentException(ErrorMessages.INVALID.getMessage());
        }
    }

    private void validateSecondOption(String option) {
        if (!option.equals("1") && !option.equals("2") && !option.equals("B")) {
            throw new IllegalArgumentException(ErrorMessages.INVALID.getMessage());
        }
    }

    public String getDeparture() {
        String departure = this.inputView.readDeparture();
        StationData.findStation(departure);
        return departure;
    }

    public String getDestination() {
        String destination = this.inputView.readDestination();
        StationData.findStation(destination);
        return destination;
    }
}
