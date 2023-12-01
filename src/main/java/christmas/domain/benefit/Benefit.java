package christmas.domain.benefit;

import christmas.domain.menu.OrderMenus;

public abstract class Benefit {

    private static final int BENEFIT_TOTAL_PRICE_CONDITION = 10000;

    private final String benefitName;
    private final int discount;

    //생성자를 protected로 선언하면 외부에서 인스턴스 생성이 불가능하고, 자식 객체에서만 확장 가능
    protected Benefit(String benefitName, int discount) {
        this.benefitName = benefitName;
        this.discount = discount;
    }

    public String getBenefitName() {return benefitName;}
    public int getDiscount() {return discount;}

    public static boolean isBenefitTarget(OrderMenus orderMenus) {
        return orderMenus.calculateTotalPrice() >= BENEFIT_TOTAL_PRICE_CONDITION;
    }

}
