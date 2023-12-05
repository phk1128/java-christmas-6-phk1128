package christmas.domain.benefit;

import christmas.domain.order.OrderMenus;
import christmas.domain.visit.Visit;

public class SpecialDayBenefit extends Benefit {

    private static final int BENEFIT_DISCOUNT = 1000;
    private static final String BENEFIT_NAME = "특별 할인";

    private SpecialDayBenefit(String name, int discount) {
        super(name, discount);
    }

    public static SpecialDayBenefit create(Visit visit, OrderMenus orderMenus) {
        int discount = calculateDiscount(visit, orderMenus);
        return new SpecialDayBenefit(BENEFIT_NAME, discount);
    }

    private static int calculateDiscount(Visit visit, OrderMenus orderMenus) {
        int discount = 0;
        if (visit.isSpecialDay() && isSatisfyCondition(orderMenus)) {
            discount -= BENEFIT_DISCOUNT;
        }
        return discount;
    }


}
