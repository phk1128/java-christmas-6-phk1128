package christmas.domain.menu;

import static christmas.util.ErrorMessage.*;

import christmas.domain.menu.MenuConstant.MenuType;
import christmas.util.ErrorMessage;
import java.util.List;

public class OrderMenus {

    private static final int MAXIMUM_ORDER_MENU_QUANTITY = 20;

    private final List<OrderMenu> detail;

    private OrderMenus(List<OrderMenu> orderMenus) {
        this.detail = orderMenus;
    }

    public static OrderMenus create(List<OrderMenu> orderMenus) {
        validateDuplicateOrderMenus(orderMenus);
        validateQuantityOrderMenus(orderMenus);
        validateOnlyDrinkOrderMenus(orderMenus);
        return new OrderMenus(orderMenus);
    }

    public int calculateTotalPrice() {
        return detail.stream()
                .mapToInt(OrderMenu::calculateOrderMenuPrice)
                .sum();
    }

    public int countBenefitTargetMenu(String menu) {
        return detail.stream()
                .filter(orderMenu -> orderMenu.isSameMenuType(menu))
                .mapToInt(OrderMenu::getQuantity)
                .sum();
    }


    private static void validateDuplicateOrderMenus(List<OrderMenu> orderMenus) {
        if (isDuplicateOrderMenu(orderMenus)) {
            throw new IllegalArgumentException(INVALID_ORDER_MENU.getMessage());
        }

    }

    private static void validateQuantityOrderMenus(List<OrderMenu> orderMenus) {
        if (isOverOrderMenusQuantity(orderMenus)) {
            throw new IllegalArgumentException(EXCEEDS_MAX_ORDER_QUANTITY.getMessage());
        }

    }

    private static void validateOnlyDrinkOrderMenus(List<OrderMenu> orderMenus) {
        if (isOnlyDrinkOrderMenus(orderMenus)) {
            throw new IllegalArgumentException(DRINK_ONLY_ORDER_NOT_ALLOWED.getMessage());
        }
    }

    private static boolean isOnlyDrinkOrderMenus(List<OrderMenu> orderMenus) {
        return orderMenus.stream()
                .filter(orderMenu -> orderMenu.getMenu().getType().equals(MenuType.DRINK))
                .count() == orderMenus.size();
    }


    private static boolean isDuplicateOrderMenu(List<OrderMenu> orderMenus) {
        return orderMenus.stream()
                .map(OrderMenu::getMenu)
                .distinct()
                .count() != orderMenus.size();
    }

    private static boolean isOverOrderMenusQuantity(List<OrderMenu> orderMenus) {
        return orderMenus.stream()
                .mapToInt(OrderMenu::getQuantity)
                .sum() > MAXIMUM_ORDER_MENU_QUANTITY;

    }

    public List<OrderMenu> getDetail() {
        return detail;
    }
}
