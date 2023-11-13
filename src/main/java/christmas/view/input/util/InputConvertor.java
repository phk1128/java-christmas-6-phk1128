package christmas.view.input.util;

import java.util.Arrays;
import java.util.List;

public class InputConvertor {

    private static final String DELIMITER = ",";

    private InputConvertor() {

    }

    public static List<String> convertStringToList(String input) {
        input = input.replace(" ", "");
        return Arrays.asList(input.split(DELIMITER));
    }

    public static int convertStringToInt(String input) {
        input = input.replace(" ", "");
        return Integer.parseInt(input);
    }


}
