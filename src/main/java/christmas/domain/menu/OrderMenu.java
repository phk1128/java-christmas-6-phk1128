package christmas.domain.menu;

import static christmas.util.ErrorMessage.INVALID_ORDER_MENU;

import java.util.regex.Pattern;

public class OrderMenu {
    private static final int MINIMUM_ORDER_MENU_QUANTITY = 0;
    private static final Pattern ORDER_MENU_FORMAT = Pattern.compile("^[a-z|A-z|ㄱ-ㅎ|가-힣|0-9]+-[0-9]+$");

    private final Menu menu;
    private final int quantity;

    private OrderMenu(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public static OrderMenu create(String orderMenu) {
        orderMenu = orderMenu.replace(" ","");
        validateOrderMenuFormat(orderMenu);

        String[] menuAndQuantity = orderMenu.split("-");
        String menuName = menuAndQuantity[0];
        int quantity = Integer.parseInt(menuAndQuantity[1]);

        validateOrderMenuQuantity(quantity);

        return new OrderMenu(Menu.findByName(menuName), quantity);
    }


    public int calculateOrderMenuPrice() {
        return menu.getPrice() * quantity;
    }

    private static void validateOrderMenuFormat(String orderMenu) {
        if (isWrongOrderMenuFormat(orderMenu)) {
            throw new IllegalArgumentException(INVALID_ORDER_MENU.getMessage());
        }

    }

    private static void validateOrderMenuQuantity(int quantity) {
        if (isLessThanMinimumQuantity(quantity)) {
            throw new IllegalArgumentException(INVALID_ORDER_MENU.getMessage());
        }
    }


    private static boolean isWrongOrderMenuFormat(String orderMenu) {
        return !ORDER_MENU_FORMAT
                .matcher(orderMenu)
                .matches();

    }

    private static boolean isLessThanMinimumQuantity(int quantity) {
        return quantity <= MINIMUM_ORDER_MENU_QUANTITY;
    }
    public Menu getMenu() {
        return this.menu;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public boolean isSameMenuType(String menuType) {
        return menu.getType().equals(menuType);
    }


}
