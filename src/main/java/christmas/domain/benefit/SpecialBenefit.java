package christmas.domain.benefit;

import christmas.domain.menu.OrderMenus;
import christmas.domain.visit.Visit;
import java.time.LocalDate;
import java.util.List;

public class SpecialBenefit extends Benefit {

    private static final String BENEFIT_NAME = "특별 할인";
    private static final int BENEFIT_TOTALPRICE_CONDITION = 10000;
    private static final int DISCOUNT_PRICE = 1000;
    private static final List<Integer> SPECIAL_DAYS = List.of(3,10,17,24,25,31);

    private SpecialBenefit(String benefitName, int discount) {
        super(benefitName, discount);
    }

    public static SpecialBenefit create(Visit visitDate, OrderMenus orderMenus) {
        int discount = calculateDiscountPrice(visitDate,orderMenus);
        return new SpecialBenefit(BENEFIT_NAME, discount);
    }

    private static int calculateDiscountPrice(Visit visitDate, OrderMenus orderMenus) {
        int discount = 0;
        if (visitDate.isSpecialDay(SPECIAL_DAYS) && isBenefitTarget(orderMenus)) {
            discount -= DISCOUNT_PRICE;
        }
        return discount;
    }

//    private static boolean isSpecialDay(LocalDate visitDate) {
//        return SPECIAL_DAYS.contains(visitDate.getDayOfMonth());
//    }

}
