package christmas.domain.benefit;

import christmas.domain.menu.MenuConstant.MenuType;
import christmas.domain.menu.OrderMenu;
import christmas.domain.menu.OrderMenus;
import christmas.domain.visit.Visit;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekendBenefit extends Benefit {

    private static final String BENEFIT_NAME = "주말 할인";
    private static final int DISCOUNT_PRICE = 2023;
    private static final String BENEFIT_MENU_TYPE = MenuType.MAIN;

    private WeekendBenefit(String benefitName, int discount) {
        super(benefitName, discount);
    }

    public static WeekendBenefit create(Visit visitDate, OrderMenus orderMenus) {
        int discount = calculateDiscountPrice(visitDate,orderMenus);
        return new WeekendBenefit(BENEFIT_NAME, discount);
    }
    private static int calculateDiscountPrice(Visit visitDate, OrderMenus orderMenus) {
        int discount = 0;
        if (visitDate.isWeekend() && isBenefitTarget(orderMenus)) {
            discount -= orderMenus.countBenefitTargetMenu(BENEFIT_MENU_TYPE) * DISCOUNT_PRICE;
        }
        return discount;
    }


}
