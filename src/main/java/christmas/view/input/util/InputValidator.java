package christmas.view.input.util;

import christmas.constant.Constant;
import christmas.constant.ErrorMessage;
import java.util.List;
import java.util.regex.Pattern;

public class InputValidator {

    private InputValidator() {

    }

    public static class Menus {

        public static void validateMenus(List<String> menus) {
            for (String menu : menus) {
                validateFormat(menu);
                validateQuantity(menu);
            }
        }

        public static void validateFormat(String menu) {
            if (!Constant.MENU_PATTERN.matcher(menu).matches()) {
                throw new IllegalArgumentException(ErrorMessage.WRONG_MENU);
            }
        }

        public static void validateQuantity(String menu) {
            if (menu.split(Constant.DELIMITER_HYPHEN)[1].equals("0")) {
                throw new IllegalArgumentException(ErrorMessage.WRONG_MENU);
            }

        }

    }

    public static class Day {

        public static void validateDay(String day) {
            validateFormat(day);
            validateRange(day);
        }

        public static void validateFormat(String day) {
            if (!Pattern.matches("^[0-9\s]+$", day)) {
                throw new IllegalArgumentException(ErrorMessage.WRONG_DAY);
            }
        }

        public static void validateRange(String day) {
            int dayNum = InputConvertor.convertStringToInt(day);
            if (dayNum <= 0 || dayNum > 31) {
                throw new IllegalArgumentException(ErrorMessage.WRONG_DAY);
            }
        }
    }

}
