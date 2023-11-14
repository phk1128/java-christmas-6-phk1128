package christmas.view.input.util;

import java.util.Arrays;
import java.util.List;


public class InputConvertor {

    private static final String DELIMITER = ",";
    private static final String SPACE = " ";

    private InputConvertor() {

    }

    public static List<String> convertStringToList(String input) {
        input = input.replace(SPACE, "");
        return Arrays.asList(input.split(DELIMITER));
    }

    public static int convertStringToInt(String input) {
        input = input.replace(SPACE, "");
        return Integer.parseInt(input);
    }


}
