package christmas.dto;

import christmas.domain.menu.OrderMenu;
import java.util.Objects;

public record OrderMenuDto(
        String menuType,
        String menuName,
        int quantity
) {

    // 컴팩트생성자로 유효성 검사
    public OrderMenuDto {
        Objects.requireNonNull(menuType);
        Objects.requireNonNull(menuName);
    }

    public static OrderMenuDto from(OrderMenu orderMenu) {
        return new OrderMenuDto(
                orderMenu.getMenu().getType(),
                orderMenu.getMenu().getName(),
                orderMenu.getQuantity()
        );
    }

}
