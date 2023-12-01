package christmas.dto;

import christmas.domain.menu.OrderMenu;
import christmas.domain.menu.OrderMenus;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record OrderMenusDto(List<OrderMenuDto> orderMenu, int totalPrice) {

    public OrderMenusDto {
        Objects.requireNonNull(orderMenu);
    }

    public static OrderMenusDto from(OrderMenus orderMenus) {
        List<OrderMenuDto> orderMenuDtos = new ArrayList<>();

        for (OrderMenu orderMenu : orderMenus.getDetail()) {
            orderMenuDtos.add(OrderMenuDto.from(orderMenu));
        }

        return new OrderMenusDto(orderMenuDtos, orderMenus.calculateTotalPrice());

    }


}
