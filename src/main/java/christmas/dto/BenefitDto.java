package christmas.dto;

import christmas.domain.benefit.Benefit;
import java.util.Objects;

public record BenefitDto(
        String benefitName,
        int discount

) {

    public BenefitDto {
        Objects.requireNonNull(benefitName);
    }

    public static BenefitDto from(Benefit benefit) {
        return new BenefitDto(
                benefit.getBenefitName(),
                benefit.getDiscount()
        );
    }

}
