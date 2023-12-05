package christmas.domain.visit;

import static christmas.util.ErrorMessage.INVALID_DATE;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class Visit {

    private static final int CURRENT_YEAR = LocalDate.now().getYear();
    private static final int CURRENT_MONTH = LocalDate.now().getMonthValue();
    private static final LocalDate CHRISTMAS = LocalDate.of(CURRENT_YEAR, 12, 25);
    private static final List<Integer> SPECIAL_DAY = List.of(3, 10, 17, 24, 25, 31);

    private final LocalDate visitDate;

    private Visit(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public static Visit create(String inputVisitDay) {
        int visitDay = convertStringToInt(inputVisitDay);
        LocalDate visitDate = convertIntToLocalDate(visitDay);
        return new Visit(visitDate);
    }


    private static int convertStringToInt(String inputVisitDay) {
        try {
            return Integer.parseInt(inputVisitDay);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_DATE.getMessage());
        }
    }

    private static LocalDate convertIntToLocalDate(int visitDay) {
        try {
            return LocalDate.of(CURRENT_YEAR, CURRENT_MONTH, visitDay);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(INVALID_DATE.getMessage());
        }
    }

    public int getDay() {
        return visitDate.getDayOfMonth();
    }

    public int getMonth() {
        return visitDate.getMonthValue();
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

    public boolean isAfterChristmas() {
        return visitDate.isAfter(CHRISTMAS);
    }

    public boolean isSpecialDay() {
        return SPECIAL_DAY.contains(visitDate.getDayOfMonth());
    }


}
