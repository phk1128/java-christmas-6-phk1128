package christmas.domain.orderMenu;

import christmas.constant.ErrorMessage;
import christmas.constant.Menu;
import christmas.constant.Menu.MenuType;
import java.util.Objects;

public class OrderMenu {

    private final Menu menu;
    private final int quantity;

    private OrderMenu(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public static OrderMenu create(Menu menu, int quantity) {
        validateQuantity(quantity);
        return new OrderMenu(menu, quantity);
    }

    public boolean isMatchMenuType(MenuType menuType) {
        return Objects.equals(menuType, menu.getType());
    }

    public int calculatePrice() {
        return menu.getPrice() * quantity;
    }

    public Menu getMenu() {
        return menu;
    }

    public String getMenuName() {
        return menu.getName();
    }

    public int getQuantity() {
        return quantity;
    }

    private static void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderMenu)) {
            return false;
        }
        OrderMenu orderMenu = (OrderMenu) o;
        return Objects.equals(this.menu, orderMenu.menu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu);
    }
}
