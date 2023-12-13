package christmas.domain.benefit;

import christmas.constant.Menu.MenuType;
import christmas.domain.orderMenu.OrderMenu;
import christmas.domain.orderMenu.OrderMenuGroup;
import christmas.domain.reservation.Reservation;

public class WeekendBenefit extends Benefit {


    private static final int DISCOUNT = 2023;
    private static final MenuType BENEFIT_MENU_TYPE = MenuType.MAIN;
    private static final String BENEFIT_NAME = "주말 할인";

    private WeekendBenefit(String name, int discount) {
        super(name, discount);
    }

    public static WeekendBenefit create(Reservation reservation, OrderMenuGroup orderMenuGroup) {
        int discount = calculateDiscount(reservation, orderMenuGroup);
        return new WeekendBenefit(BENEFIT_NAME, discount);
    }

    private static int calculateDiscount(Reservation reservation, OrderMenuGroup orderMenuGroup) {
        int discount = 0;
        if (!isSatisfyCondition(orderMenuGroup)) {
            return discount;
        }
        for (OrderMenu orderMenu : orderMenuGroup.getOrderMenus()) {
            if (orderMenu.isMatchMenuType(BENEFIT_MENU_TYPE) && reservation.isWeekend()) {
                discount -= (DISCOUNT * orderMenu.getQuantity());
            }
        }
        return discount;
    }
}

