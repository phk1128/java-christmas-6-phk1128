package christmas.domain.benefit;

import christmas.domain.orderMenu.OrderMenuGroup;
import christmas.domain.reservation.Reservation;

public class ChristmasBenefit extends Benefit {

    private static final int DEFAULT_DISCOUNT = 1000;
    private static final int INCREASE_DISCOUNT = 100;
    private static final String BENEFIT_NAME = "크리스마스 디데이 할인";

    private ChristmasBenefit(String name, int discount) {
        super(name, discount);
    }

    public static ChristmasBenefit create(Reservation reservation, OrderMenuGroup orderMenuGroup) {
        int discount = calculateDiscount(reservation, orderMenuGroup);
        return new ChristmasBenefit(BENEFIT_NAME, discount);
    }

    private static int calculateDiscount(Reservation reservation, OrderMenuGroup orderMenuGroup) {
        int discount = 0;
        if (isSatisfyCondition(orderMenuGroup) && !reservation.isAfterChristmas()) {
            discount -= (DEFAULT_DISCOUNT + (INCREASE_DISCOUNT * (reservation.getDayOfMonth() - 1)));
        }
        return discount;
    }

}

