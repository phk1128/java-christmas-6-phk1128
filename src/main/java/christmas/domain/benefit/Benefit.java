package christmas.domain.Benefit;

import christmas.domain.menu.OrderMenus;

public abstract class BenefitPolicy {

    private final String benefitName;
    private final int benefitTargetCondition;

    //생성자를 protected로 선언하면 외부에서 인스턴스 생성이 불가능하고, 자식 객체에서만 확장 가능
    protected BenefitPolicy(String policyName, int benefitTargetCondition) {
        this.benefitName = policyName;
        this.benefitTargetCondition = benefitTargetCondition;
    }

    public String getBenefitName() {return benefitName;}

    public final int discount(OrderMenus orderMenus) {
        return calculateDiscountPrice(orderMenus);
    }

    public boolean isBenefitTarget(int totalPrice) {return totalPrice >= benefitTargetCondition; }

    protected abstract int calculateDiscountPrice(OrderMenus orderMenus);

    public abstract boolean isBenefitDay();



}
