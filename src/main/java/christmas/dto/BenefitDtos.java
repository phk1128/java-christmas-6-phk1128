package christmas.dto;

import christmas.domain.benefit.Benefit;
import christmas.domain.benefit.Benefits;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record BenefitDtos(
        List<BenefitDto> benefitDtos,
        int totalDiscount,
        String giftMenu,
        int giftQuantity
) {

    public BenefitDtos {
        Objects.requireNonNull(benefitDtos);
        Objects.requireNonNull(giftMenu);
    }

    public static BenefitDtos from(Benefits benefits) {
        List<BenefitDto> benefitDtos = new ArrayList<>();
        for (Benefit benefit : benefits.getBenefits()) {
            benefitDtos.add(BenefitDto.from(benefit));
        }
        return new BenefitDtos(
                benefitDtos,
                benefits.calculateTotalDiscount(),
                benefits.getGift().getName(),
                benefits.getGiftQuantity()
        );
    }
}
