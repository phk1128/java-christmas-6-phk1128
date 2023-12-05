package christmas.domain.benefit;

import christmas.domain.order.Menu.MenuType;
import christmas.domain.order.OrderMenus;
import christmas.domain.visit.Visit;

public class WeekdayBenefit extends Benefit {

    private static final MenuType targetMenuType = MenuType.DESSERT;
    private static final int BENEFIT_DISCOUNT = 2_023;
    private static final String BENEFIT_NAME = "평일 할인";

    private WeekdayBenefit(String name, int discount) {
        super(name, discount);
    }

    public static WeekdayBenefit create(Visit visit, OrderMenus orderMenus) {
        int discount = calculateDiscount(visit, orderMenus);
        return new WeekdayBenefit(BENEFIT_NAME, discount);
    }

    private static int calculateDiscount(Visit visit, OrderMenus orderMenus) {
        int discount = 0;
        if (visit.isWeekday() && isSatisfyCondition(orderMenus)) {
            int count = orderMenus.countBenefitMenuType(targetMenuType);
            discount -= (BENEFIT_DISCOUNT * count);
        }
        return discount;
    }
}
