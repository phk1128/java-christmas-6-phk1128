package christmas.domain.orderMenu;

import christmas.constant.ErrorMessage;
import christmas.constant.Menu.MenuType;
import java.util.List;

public class OrderMenuGroup {

    private static final int MAXIMUM_TOTAL_QUANTITY = 20;

    private final List<OrderMenu> orderMenus;

    private OrderMenuGroup(List<OrderMenu> orderMenus) {
        this.orderMenus = orderMenus;
    }

    public static OrderMenuGroup create(List<OrderMenu> orderMenus) {
        validateTotalQuantity(orderMenus);
        validateOnlyDrink(orderMenus);
        validateDuplicate(orderMenus);

        return new OrderMenuGroup(orderMenus);
    }

    public List<OrderMenu> getOrderMenus() {
        return orderMenus;
    }

    public int calculateTotalPrice() {
        return orderMenus.stream()
                .mapToInt(OrderMenu::calculatePrice)
                .sum();
    }

    private static void validateDuplicate(List<OrderMenu> orderMenus) {
        if (isDuplicate(orderMenus)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    private static boolean isDuplicate(List<OrderMenu> orderMenus) {
        return orderMenus.stream()
                .distinct()
                .count() != orderMenus.size();
    }

    private static void validateTotalQuantity(List<OrderMenu> orderMenus) {
        if (isExceedsTotalQuantity(orderMenus)) {
            throw new IllegalArgumentException(ErrorMessage.EXCEEDS_MAXIMUM_ORDER_QUANTITY.getMessage());
        }
    }

    private static void validateOnlyDrink(List<OrderMenu> orderMenus) {
        if (isOnlyDrink(orderMenus)) {
            throw new IllegalArgumentException(ErrorMessage.REJECT_ONLY_DRINK_ORDER.getMessage());
        }
    }

    private static boolean isOnlyDrink(List<OrderMenu> orderMenus) {
        return orderMenus.stream()
                .allMatch(orderMenu -> orderMenu.isMatchMenuType(MenuType.DRINK));
    }

    private static boolean isExceedsTotalQuantity(List<OrderMenu> orderMenus) {
        return orderMenus.stream()
                .mapToInt(OrderMenu::getQuantity)
                .sum() > MAXIMUM_TOTAL_QUANTITY;
    }
}
