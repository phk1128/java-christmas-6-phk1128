package christmas.util;

import java.util.Arrays;
import java.util.List;

public class Convertor {

    private Convertor() {

    }

    public static int convertToInt(String str, String errorMessage) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static List<String> convertToStringList(String input) {
        return Arrays.stream(input.split(","))
                .toList();
    }


}
