package christmas.domain.benefit;

import christmas.domain.menu.Menu;
import christmas.domain.menu.OrderMenus;

public class GiftBenefit extends Benefit{
    private static final int GIFT_TOTAL_PRICE_CONDITION = 120000;
    private static final String BENEFIT_NAME = "증정 이벤트";
    private static final String GIFT_ITEM = Menu.CHAMPAGNE.getName();
    private static final int GIFT_QUANTITY = 1;

    private GiftBenefit(String benefitName, int discount) {
        super(benefitName, discount);
    }

    public static GiftBenefit create(OrderMenus orderMenus) {
        int discount = calculateGiftDiscount(orderMenus);
        return new GiftBenefit(BENEFIT_NAME, discount);
    }

    public static int calculateGiftDiscount(OrderMenus orderMenus) {
        int discount = 0;
        if (isGiftTarget(orderMenus)) {
            discount -= Menu.findByName(GIFT_ITEM).getPrice() * GIFT_QUANTITY;
        }
        return discount;
    }

    private static boolean isGiftTarget(OrderMenus orderMenus) {
        return orderMenus.calculateTotalPrice() >= GIFT_TOTAL_PRICE_CONDITION;
    }

    public String checkGiftItem() {
        if (getDiscount() < 0) {
            return GIFT_ITEM;
        }
        return Menu.NONE.getName();
    }

    public int checkGiftQuantity() {
        if (getDiscount() < 0) {
            return GIFT_QUANTITY;
        }
        return 0;
    }
}
