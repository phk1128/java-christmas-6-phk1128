package christmas.view.input.util;

import java.util.List;
import java.util.regex.Pattern;

public class InputValidator {

    public static class Menus {

        public static void validateMenus(List<String> menus) {
            for (String menu : menus) {
                validateFormat(menu);
                validateQuantity(menu);
            }
        }

        public static void validateFormat(String menu) {
            if (!Pattern.matches("^[a-z|A-z|ㄱ-ㅎ|가-힣|0-9|\s]+-[0-9\s]+$", menu)) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }

        public static void validateQuantity(String menu) {
            if (menu.split("-")[1].equals("0")) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }

        }

    }

}
