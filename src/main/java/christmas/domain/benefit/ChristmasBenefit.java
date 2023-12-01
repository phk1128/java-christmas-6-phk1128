package christmas.domain.benefit;

import christmas.domain.menu.OrderMenus;
import christmas.domain.visit.Visit;
import java.time.LocalDate;

public class ChristmasBenefit extends Benefit {
    private static final String BENEFIT_NAME = "크리스마스 디데이 할인";
    private static final int DISCOUNT_DEFAULT_PRICE = 1000;
    private static final int DISCOUNT_PRICE = 100;
    private static final LocalDate CHRISTMAS = LocalDate.of(2023, 12, 25);

    private ChristmasBenefit(String benefitName, int discount) {
        super(benefitName, discount);
    }

    public static ChristmasBenefit create(Visit visitDate, OrderMenus orderMenus) {
        int discount = calculateDiscountPrice(visitDate, orderMenus);
        return new ChristmasBenefit(BENEFIT_NAME, discount);
    }

    private static int calculateDiscountPrice(Visit visitDate, OrderMenus orderMenus) {
        int discount = 0;
        if (!visitDate.isAfterChristmas(CHRISTMAS) && isBenefitTarget(orderMenus)) {
            int dayOfMonth = visitDate.getVisitDay();
            int increasedDiscount = (dayOfMonth - 1) * DISCOUNT_PRICE;
            discount -= (DISCOUNT_DEFAULT_PRICE + increasedDiscount);
        }
        return discount;
    }


}
