package christmas.dto;

import christmas.domain.benefit.Benefit;
import java.util.Objects;

public record BenefitDto(
        String name,
        int discount
) {

    public BenefitDto {
        Objects.requireNonNull(name);
    }

    public static BenefitDto from(Benefit benefit) {
        return new BenefitDto(benefit.getName(), benefit.getDiscount());
    }
}
