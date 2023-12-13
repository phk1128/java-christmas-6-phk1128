package christmas.domain.reservation;

import christmas.constant.ErrorMessage;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Reservation {

    private static final int YEAR = LocalDate.now().getYear();
    private static final int MONTH = LocalDate.now().getMonthValue();
    private static final List<Integer> SPECIAL_DAYS = List.of(3, 10, 17, 24, 25, 31);
    private static final LocalDate CHRISTMAS = LocalDate.of(2023, 12, 25);

    private final LocalDate visitDate;

    private Reservation(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public static Reservation create(int inputVisitDay) {
        LocalDate localDate = convertToLocalDate(inputVisitDay);
        return new Reservation(localDate);
    }

    private static LocalDate convertToLocalDate(int inputVisitDay) {
        try {
            return LocalDate.of(YEAR, MONTH, inputVisitDay);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DAY.getMessage());
        }
    }

    public int getDayOfMonth() {
        return visitDate.getDayOfMonth();
    }

    public boolean isWeekday() {
        DayOfWeek dayOfWeek = visitDate.getDayOfWeek();
        return Objects.equals(dayOfWeek, DayOfWeek.SUNDAY) ||
                Objects.equals(dayOfWeek, DayOfWeek.MONDAY) ||
                Objects.equals(dayOfWeek, DayOfWeek.TUESDAY) ||
                Objects.equals(dayOfWeek, DayOfWeek.WEDNESDAY) ||
                Objects.equals(dayOfWeek, DayOfWeek.THURSDAY);
    }

    public boolean isWeekend() {
        DayOfWeek dayOfWeek = visitDate.getDayOfWeek();
        return Objects.equals(dayOfWeek, DayOfWeek.FRIDAY) ||
                Objects.equals(dayOfWeek, DayOfWeek.SATURDAY);
    }

    public boolean isAfterChristmas() {
        return visitDate.isAfter(CHRISTMAS);
    }

    public boolean isSpecialDay() {
        return SPECIAL_DAYS.contains(visitDate.getDayOfMonth());
    }


}
