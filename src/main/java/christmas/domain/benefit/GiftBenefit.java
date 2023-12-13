package christmas.domain.benefit;

import christmas.constant.Menu;
import christmas.domain.orderMenu.OrderMenuGroup;

public class GiftBenefit extends Benefit {

    private static final int BENEFIT_TOTAL_PRICE_CONDITION = 120000;
    private static final String BENEFIT_NAME = "증정 이벤트";
    private static final Menu GIFT_MENU = Menu.CHAMPAGNE;
    private static final int GIFT_MENU_QUANTITY = 1;

    private GiftBenefit(String name, int discount) {
        super(name, discount);
    }

    public static GiftBenefit create(OrderMenuGroup orderMenuGroup) {
        int discount = calculateDiscount(orderMenuGroup);
        return new GiftBenefit(BENEFIT_NAME, discount);
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

    private static int calculateDiscount(OrderMenuGroup orderMenuGroup) {
        int discount = 0;
        if (orderMenuGroup.calculateTotalPrice() >= BENEFIT_TOTAL_PRICE_CONDITION) {
            discount -= GIFT_MENU.getPrice();
        }
        return discount;
    }


}
