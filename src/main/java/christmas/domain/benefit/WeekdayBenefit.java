package christmas.domain.benefit;

import christmas.domain.menu.MenuConstant.MenuType;
import christmas.domain.menu.OrderMenu;
import christmas.domain.menu.OrderMenus;
import christmas.domain.visit.Visit;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekdayBenefit extends Benefit {

    private static final String BENEFIT_NAME = "평일 할인";
    private static final int DISCOUNT_PRICE = 2023;
    private static final String BENEFIT_MENU_TYPE = MenuType.DESSERT;

    private WeekdayBenefit(String benefitName, int discount) {
        super(benefitName, discount);
    }

    public static WeekdayBenefit create(Visit visitDate, OrderMenus orderMenus) {
        int discount = calculateDiscountPrice(visitDate,orderMenus);
        return new WeekdayBenefit(BENEFIT_NAME, discount);
    }
    private static int calculateDiscountPrice(Visit visitDate, OrderMenus orderMenus) {
        int discount = 0;
        if (visitDate.isWeekday() && isBenefitTarget(orderMenus)) {
            discount -= orderMenus.countBenefitTargetMenu(BENEFIT_MENU_TYPE) * DISCOUNT_PRICE;
        }
        return discount;
    }

//    private static boolean isWeekday(LocalDate visitDate) {
//        DayOfWeek dayOfWeek = visitDate.getDayOfWeek();
//        return dayOfWeek == DayOfWeek.SUNDAY ||
//                dayOfWeek == DayOfWeek.MONDAY ||
//                dayOfWeek == DayOfWeek.TUESDAY ||
//                dayOfWeek == DayOfWeek.WEDNESDAY ||
//                dayOfWeek == DayOfWeek.THURSDAY;
//    }

//    private static int getBenefitMenuTypeCount(OrderMenus orderMenus) {
//        return orderMenus.getDetail().stream()
//                .filter(orderMenu -> orderMenu.getMenu().getType().equals(BENEFIT_MENU_TYPE))
//                .mapToInt(OrderMenu::getQuantity)
//                .sum();
//    }

}
