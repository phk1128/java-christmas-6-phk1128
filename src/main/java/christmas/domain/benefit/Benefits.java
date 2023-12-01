package christmas.domain.benefit;

import christmas.domain.menu.Menu;
import java.util.Arrays;
import java.util.List;

public class Benefits {

    private final List<Benefit> detail;

    private Benefits(List<Benefit> benefits) {
        this.detail = benefits;
    }

    public static Benefits create(Benefit... benefits) {
        return new Benefits(Arrays.stream(benefits).toList());
    }

    public int calculateTotalDiscount() {
        return detail.stream()
                .mapToInt(Benefit::getDiscount)
                .sum();
    }

    public int calculateTotalDiscountExcludedGift() {
        return detail.stream()
                .filter(benefit -> !(benefit instanceof GiftBenefit))
                .mapToInt(Benefit::getDiscount)
                .sum();
    }

    public String getGiftItem() {
        return detail.stream()
                .filter(GiftBenefit.class::isInstance)
                .map(benefit -> ((GiftBenefit) benefit).checkGiftItem())
                .findFirst()
                .orElse(Menu.NONE.getName());
    }

    public int getGiftItemQuantity() {
        return detail.stream()
                .filter(GiftBenefit.class::isInstance)
                .map(benefit -> ((GiftBenefit) benefit).checkGiftQuantity())
                .findFirst()
                .orElse(0);
    }

    public List<Benefit> getDetail() {
        return detail;
    }
}
