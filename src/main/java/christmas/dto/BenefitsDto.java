package christmas.dto;

import christmas.domain.benefit.Benefit;
import christmas.domain.benefit.Benefits;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record BenefitsDto(
        List<BenefitDto> benefitDtos,
        int totalDiscountPrice,
        String giftItem,
        int giftQuantity
) {
    public BenefitsDto {
        Objects.requireNonNull(benefitDtos);
    }

    public static BenefitsDto from(Benefits benefits) {
        List<BenefitDto> benefitDtos = new ArrayList<>();
        for (Benefit benefit : benefits.getDetail()) {
            benefitDtos.add(BenefitDto.from(benefit));
        }

        return new BenefitsDto(
                benefitDtos,
                benefits.calculateTotalDiscount(),
                benefits.getGiftItem(),
                benefits.getGiftItemQuantity()
        );
    }
}
