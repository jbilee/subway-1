package subway.ui;

import subway.helpers.Console;
import subway.ui.constants.InputPrompts;

public class InputView {
    public String readOption() {
        System.out.println(InputPrompts.OPTION_PROMPT.getText());
        return Console.readLine().strip();
    }

    public String readDeparture() {
        System.out.println(InputPrompts.DEPARTURE_PROMPT.getText());
        return Console.readLine().strip();
    }

    public String readDestination() {
        System.out.println(InputPrompts.DESTINATION_PROMPT.getText());
        return Console.readLine().strip();
    }
}
