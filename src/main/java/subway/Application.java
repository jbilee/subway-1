package subway;

import subway.domain.Handler;
import subway.helpers.InputController;
import subway.ui.InputView;
import subway.ui.constants.ErrorMessages;

public class Application {
    public static void main(String[] args) {
        InputController inputController = new InputController(new InputView());

        String option = inputController.getOption();
        if (option.equals("Q")) {
            return;
        }

        String departure;
        String destination;

        while (true) {
            try {
                departure = inputController.getDeparture();
                destination = inputController.getDestination();
                if (departure.equals(destination)) {
                    throw new IllegalArgumentException(ErrorMessages.DUPLICATE.getMessage());
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }



        Handler handler = new Handler();
    }
}
