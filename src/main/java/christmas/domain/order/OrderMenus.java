package christmas.domain.order;

import static christmas.util.ErrorMessage.INVALID_ORDER;

import christmas.domain.order.Menu.MenuType;
import java.util.List;
import java.util.Objects;

public class OrderMenus {

    private static final int MAXIMUM_ORDER_MENUS_QUANTITY = 20;

    private final List<OrderMenu> orderMenus;

    private OrderMenus(List<OrderMenu> orderMenus) {
        this.orderMenus = orderMenus;
    }

    public static OrderMenus create(List<OrderMenu> orderMenus) {
        validateQuantity(orderMenus);
        validateDuplicate(orderMenus);
        validateOnlyDrink(orderMenus);

        return new OrderMenus(orderMenus);
    }

    public List<OrderMenu> getOrderMenus() {
        return orderMenus;
    }

    public int countBenefitMenuType(MenuType menuType) {
        return orderMenus.stream()
                .filter(orderMenu -> Objects.equals(orderMenu.getMenu().getType(), menuType))
                .mapToInt(OrderMenu::getQuantity)
                .sum();
    }

    public int calculateTotalPrice() {
        return orderMenus.stream()
                .mapToInt(OrderMenu::calculatePrice)
                .sum();
    }

    private static void validateOnlyDrink(List<OrderMenu> orderMenus) {
        if (isOnlyDrinks(orderMenus)) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    private static boolean isOnlyDrinks(List<OrderMenu> orderMenus) {
        return orderMenus.stream()
                .allMatch(orderMenu -> Objects.equals(orderMenu.getMenu().getType(), MenuType.DRINK));
    }

    private static void validateDuplicate(List<OrderMenu> orderMenus) {
        if (isDuplicate(orderMenus)) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    private static boolean isDuplicate(List<OrderMenu> orderMenus) {
        return orderMenus.stream()
                .map(OrderMenu::getMenu)
                .distinct()
                .count() != orderMenus.size();
    }

    private static void validateQuantity(List<OrderMenu> orderMenus) {
        if (isExceedsMaximumOrderMenusQuantity(orderMenus)) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    private static boolean isExceedsMaximumOrderMenusQuantity(List<OrderMenu> orderMenus) {
        return orderMenus.stream()
                .mapToInt(OrderMenu::getQuantity)
                .sum() > MAXIMUM_ORDER_MENUS_QUANTITY;
    }

}
