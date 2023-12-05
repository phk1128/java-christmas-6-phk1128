package christmas.dto;

import christmas.domain.order.OrderMenu;
import christmas.domain.order.OrderMenus;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record OrderMenuDtos(
        List<OrderMenuDto> orderMenuDtos,
        int totalPrice
) {
    public OrderMenuDtos {
        Objects.requireNonNull(orderMenuDtos);
    }

    public static OrderMenuDtos from(OrderMenus orderMenus) {
        List<OrderMenuDto> orderMenuDtos = new ArrayList<>();

        for (OrderMenu orderMenu : orderMenus.getOrderMenus()) {
            orderMenuDtos.add(OrderMenuDto.from(orderMenu));
        }
        return new OrderMenuDtos(orderMenuDtos, orderMenus.calculateTotalPrice());
    }
}
