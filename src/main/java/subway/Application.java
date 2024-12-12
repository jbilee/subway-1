package subway;

import subway.helpers.InputController;
import subway.ui.InputView;
import subway.ui.constants.ErrorMessages;

public class Application {
    public static void main(String[] args) {
        InputController inputController = new InputController(new InputView());
        ServiceController serviceController = new ServiceController();

        while (true) {
            String option = inputController.getOption();
            if (option.equals("Q")) {
                break;
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

            serviceController.handleResults(departure, destination, option);
            System.out.print("\n");
        }
    }
}
