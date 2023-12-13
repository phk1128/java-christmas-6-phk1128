package christmas.domain.benefit;

import christmas.constant.Menu;
import java.util.Arrays;
import java.util.List;

public class BenefitGroup {

    private final List<Benefit> benefits;

    private BenefitGroup(List<Benefit> benefits) {
        this.benefits = benefits;
    }

    public static BenefitGroup create(Benefit... benefits) {
        List<Benefit> bebefitList = Arrays.stream(benefits).toList();
        return new BenefitGroup(bebefitList);
    }

    public List<Benefit> getBenefits() {
        return benefits;
    }

    public int calculateTotalDiscount() {
        return benefits.stream()
                .mapToInt(Benefit::getDiscount)
                .sum();
    }

    public Menu getGiftMenu() {
        return benefits.stream()
                .filter(GiftBenefit.class::isInstance)
                .findAny()
                .map(benefit -> ((GiftBenefit) benefit).getGiftMenu())
                .orElse(Menu.NONE);
    }

    public int getGiftQuantity() {
        return benefits.stream()
                .filter(GiftBenefit.class::isInstance)
                .findAny()
                .map(benefit -> ((GiftBenefit) benefit).getGiftMenuQuantity())
                .orElse(0);
    }

    public int calculateDiscountExcludedGift() {
        return benefits.stream()
                .filter(benefit -> !(benefit instanceof GiftBenefit))
                .mapToInt(Benefit::getDiscount)
                .sum();
    }
}
