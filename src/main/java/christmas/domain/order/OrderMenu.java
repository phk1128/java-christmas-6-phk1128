package christmas.domain.order;

import static christmas.util.ErrorMessage.INVALID_ORDER;

import java.util.Objects;
import java.util.regex.Pattern;

public class OrderMenu {

    private static final Pattern ORDER_FORMAT = Pattern.compile("^[a-z|A-z|ㄱ-ㅎ|가-힣|0-9]+-[0-9]+$");

    private final Menu menu;
    private final int quantity;

    private OrderMenu(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public static OrderMenu create(String inputOrderMenu) {
        validateFormat(inputOrderMenu);
        validateQuantity(inputOrderMenu);
        String[] menuAndQuantity = inputOrderMenu.split("-");
        Menu menu = Menu.findByMenuName(menuAndQuantity[0]);
        int quantity = Integer.parseInt(menuAndQuantity[1]);
        return new OrderMenu(menu, quantity);
    }

    public Menu getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }

    public int calculatePrice() {
        return menu.getPrice() * quantity;
    }

    private static void validateQuantity(String inputOrderMenu) {
        if (isWrongQuantity(inputOrderMenu)) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    private static boolean isWrongQuantity(String inputOrderMenu) {
        return Objects.equals(inputOrderMenu.split("-")[1], "0");
    }

    private static void validateFormat(String inputOrderMenu) {
        if (isWrongFormat(inputOrderMenu)) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    private static boolean isWrongFormat(String inputOrderMenu) {
        return !ORDER_FORMAT.matcher(inputOrderMenu).matches();
    }


}
