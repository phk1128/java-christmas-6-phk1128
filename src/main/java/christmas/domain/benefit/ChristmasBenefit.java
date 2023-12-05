package christmas.domain.benefit;

import christmas.domain.order.OrderMenus;
import christmas.domain.visit.Visit;

public class ChristmasBenefit extends Benefit {

    private static final int BENEFIT_DISCOUNT = 1000;
    private static final int INCREASE_DISCOUNT = 100;
    private static final String BENEFIT_NAME = "크리스마스 디데이 할인";

    private ChristmasBenefit(String name, int discount) {
        super(name, discount);
    }

    public static ChristmasBenefit create(Visit visit, OrderMenus orderMenus) {
        int discount = calculateDiscount(visit, orderMenus);
        return new ChristmasBenefit(BENEFIT_NAME, discount);
    }

    private static int calculateDiscount(Visit visit, OrderMenus orderMenus) {
        int discount = 0;
        if (!visit.isAfterChristmas() && isSatisfyCondition(orderMenus)) {
            discount -= BENEFIT_DISCOUNT + (INCREASE_DISCOUNT * (visit.getDay() - 1));
        }
        return discount;
    }
}
