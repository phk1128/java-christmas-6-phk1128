package christmas.domain.gift;

import christmas.domain.benefit.GiftBenefit;
import christmas.domain.menu.Menu;
import christmas.domain.menu.OrderMenus;

public class Gift{

    private static final int GIFT_TOTAL_PRICE_CONDITION = 120000;
    private static final String BENEFIT_NAME = "증정 이벤트";
    private static final String GIFT_ITEM = Menu.CHAMPAGNE.getName();
    private static final int GIFT_QUANTITY = 1;


    private final String benefitName;
    private final String giftItem;
    private final int giftQuantity;
    private final int discount;


    private Gift(String benefitName, String giftItem, int giftQuantity, int discount) {
        this.benefitName = benefitName;
        this.giftItem = giftItem;
        this.giftQuantity = giftQuantity;
        this.discount = discount;
    }

    public String getBenefitName() {
        return benefitName;
    }

    public String getGiftItem() {
        return giftItem;
    }

    public int getGiftQuantity() {
        return giftQuantity;
    }

    public int getDiscount() {
        return discount;
    }

    public static Gift create(OrderMenus orderMenus) {
        int discount = calculateGiftDiscount(orderMenus);
        return new Gift(BENEFIT_NAME, GIFT_ITEM, GIFT_QUANTITY, discount);
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
}
