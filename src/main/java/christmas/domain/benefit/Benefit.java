package christmas.domain.benefit;

import christmas.domain.order.OrderMenus;

public abstract class Benefit {

    private static final int BENEFIT_TOTAL_PRICE_CONDITION = 10000;

    private final String name;
    private final int discount;

    protected Benefit(String name, int discount) {
        this.name = name;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public int getDiscount() {
        return discount;
    }

    protected static boolean isSatisfyCondition(OrderMenus orderMenus) {
        return orderMenus.calculateTotalPrice() >= BENEFIT_TOTAL_PRICE_CONDITION;
    }
}
