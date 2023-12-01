package christmas.domain.visit;

import static christmas.util.ErrorMessage.*;

import christmas.util.ErrorMessage;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;

public class Visit {

    private static final int CURRENT_YEAR = LocalDate.now().getYear();
    private static final int CURRENT_MONTH  = LocalDate.now().getMonthValue();

    private final LocalDate visitDate;

    private Visit(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public static Visit create(String visitDay) {
        LocalDate convertedVisitDay = convertDayToDate(convertStringToInt(visitDay));
        return new Visit(convertedVisitDay);

    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    private static int convertStringToInt(String visitDay) {
        try {
            return Integer.parseInt(visitDay);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_VISIT_DATE.getMessage());
        }
    }

    private static LocalDate convertDayToDate(int visitDay) {
        try {
            return LocalDate.of(CURRENT_YEAR, CURRENT_MONTH, visitDay);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(INVALID_VISIT_DATE.getMessage());
        }
    }

    public int getVisitDay() {
        return visitDate.getDayOfMonth();
    }

    public boolean isWeekday() {
        DayOfWeek dayOfWeek = visitDate.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SUNDAY ||
                dayOfWeek == DayOfWeek.MONDAY ||
                dayOfWeek == DayOfWeek.TUESDAY ||
                dayOfWeek == DayOfWeek.WEDNESDAY ||
                dayOfWeek == DayOfWeek.THURSDAY;
    }

    public boolean isWeekend() {
        DayOfWeek dayOfWeek = visitDate.getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY ||
                dayOfWeek == DayOfWeek.SATURDAY;
    }

    public boolean isSpecialDay(List<Integer> specialDay) {
        return specialDay.contains(visitDate.getDayOfMonth());
    }

    public boolean isAfterChristmas(LocalDate christmasDate) {
        return visitDate.isAfter(christmasDate);
    }
}
