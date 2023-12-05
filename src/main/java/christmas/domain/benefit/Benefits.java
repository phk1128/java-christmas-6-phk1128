package christmas.domain.benefit;

import christmas.domain.order.Menu;
import java.util.Arrays;
import java.util.List;

public class Benefits {

    private final List<Benefit> benefits;

    private Benefits(List<Benefit> benefits) {
        this.benefits = benefits;
    }

    public static Benefits create(Benefit... benefits) {
        return new Benefits(Arrays.stream(benefits).toList());
    }

    public List<Benefit> getBenefits() {
        return benefits;
    }

    public int calculateTotalDiscount() {
        return benefits.stream()
                .mapToInt(Benefit::getDiscount)
                .sum();
    }

    public int calculateTotalDiscountExcludeGift() {
        return benefits.stream()
                .filter(benefit -> !(benefit instanceof GiftBenefit))
                .mapToInt(Benefit::getDiscount)
                .sum();
    }

    public Menu getGift() {
        return benefits.stream()
                .filter(GiftBenefit.class::isInstance)
                .map(benefit -> ((GiftBenefit) benefit).getGiftMenu())
                .findAny()
                .orElse(Menu.NONE);
    }

    public int getGiftQuantity() {
        return benefits.stream()
                .filter(benefit -> benefit instanceof GiftBenefit)
                .map(benefit -> ((GiftBenefit) benefit).getGiftMenuQuantity())
                .findAny()
                .orElse(0);
    }
}
