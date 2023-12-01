package christmas.dto;

import christmas.domain.visit.Visit;

public record VisitDto(
        int month,
        int day
) {

    public static VisitDto from(Visit visit) {
        return new VisitDto(visit.getVisitDate().getMonthValue(),visit.getVisitDate().getDayOfMonth());
    }
}
