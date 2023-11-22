package christmas.domain.discount;

public class DiscountPolicy {

    private final String policyName;

    //생성자를 protected로 선언하면 외부에서 인스턴스 생성이 불가능하고, 자식 객체에서만 확장 가능
    protected DiscountPolicy(String policyName) {
        this.policyName = policyName;
    }



}
