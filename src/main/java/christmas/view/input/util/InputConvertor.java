package christmas.view.input.util;

import christmas.constant.Constant;
import java.util.Arrays;
import java.util.List;


public class InputConvertor {

    private InputConvertor() {

    }

    public static List<String> convertStringToList(String input) {
        input = input.replace(Constant.SPACE, "");
        return Arrays.asList(input.split(Constant.DELIMITER_COMMA));
    }

    public static int convertStringToInt(String input) {
        input = input.replace(Constant.SPACE, "");
        return Integer.parseInt(input);
    }


}
