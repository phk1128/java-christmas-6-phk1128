package christmas.domain.benefit;

import christmas.domain.order.Menu;
import christmas.domain.order.OrderMenus;

public class GiftBenefit extends Benefit {

    private static final int GIFT_TOTAL_PRICE_CONDITION = 120_000;
    private static final Menu GIFT_MENU = Menu.CHAMPAGNE;
    private static final int GIFT_MENU_QUANTITY = 1;
    private static final String BENEFIT_NAME = "증정 이벤트";

    private GiftBenefit(String name, int discount) {
        super(name, discount);
    }

    public static GiftBenefit create(OrderMenus orderMenus) {
        int discount = calculateDiscount(orderMenus);
        return new GiftBenefit(BENEFIT_NAME, discount);
    }

    private static int calculateDiscount(OrderMenus orderMenus) {
        int discount = 0;
        if (isSatisfyGiftCondition(orderMenus)) {
            discount -= (GIFT_MENU.getPrice() * GIFT_MENU_QUANTITY);
        }
        return discount;
    }

    private static boolean isSatisfyGiftCondition(OrderMenus orderMenus) {
        return orderMenus.calculateTotalPrice() >= GIFT_TOTAL_PRICE_CONDITION;
    }

    public Menu getGiftMenu() {
        if (getDiscount() != 0) {
            return GIFT_MENU;
        }
        return Menu.NONE;
    }

    public int getGiftMenuQuantity() {
        if (getDiscount() != 0) {
            return GIFT_MENU_QUANTITY;
        }
        return 0;
    }


}
