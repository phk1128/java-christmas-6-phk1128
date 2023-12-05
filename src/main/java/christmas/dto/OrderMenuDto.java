package christmas.dto;

import christmas.domain.order.OrderMenu;
import java.util.Objects;

public record OrderMenuDto(
        String menuName,
        int quantity
) {
    public OrderMenuDto {
        Objects.requireNonNull(menuName);
    }

    public static OrderMenuDto from(OrderMenu orderMenu) {
        return new OrderMenuDto(orderMenu.getMenu().getName(), orderMenu.getQuantity());
    }
}
