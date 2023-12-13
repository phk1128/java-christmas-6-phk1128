package christmas.domain.benefit;

import christmas.domain.orderMenu.OrderMenuGroup;
import christmas.domain.reservation.Reservation;

public class SpecialDayBenefit extends Benefit {

    private static final int DISCOUNT = 1000;
    private static final String BENEFIT_NAME = "특별 할인";

    private SpecialDayBenefit(String name, int discount) {
        super(name, discount);
    }

    public static SpecialDayBenefit create(Reservation reservation, OrderMenuGroup orderMenuGroup) {
        int discount = calculateDiscount(reservation, orderMenuGroup);
        return new SpecialDayBenefit(BENEFIT_NAME, discount);
    }

    private static int calculateDiscount(Reservation reservation, OrderMenuGroup orderMenuGroup) {
        int discount = 0;
        if (isSatisfyCondition(orderMenuGroup) && reservation.isSpecialDay()) {
            discount -= DISCOUNT;
        }
        return discount;
    }

}

