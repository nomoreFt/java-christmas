package christmas.v0.event;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DecemberEventCalendar extends EventCalendar{

    private static final int CHRISTMAS_DAY = 25;
    private final LocalDate christmas;

    public DecemberEventCalendar(int eventYear, int eventMonth, int eventStartDay, int eventEndDay) {
        super(eventYear, eventMonth, eventStartDay, eventEndDay); // 12월 전체를 대상으로 함
        christmas = LocalDate.of(eventYear, eventMonth, CHRISTMAS_DAY);
    }

    // 크리스마스까지 D-day 계산, christmas 너
    public long daysUntilChristmas(LocalDate reserveDate) {
        return ChronoUnit.DAYS.between(reserveDate, christmas);
    }

    //1~25일 인지 확인
    public boolean isChristmasDday(LocalDate reserveDate) {
        return reserveDate.getDayOfMonth() <= CHRISTMAS_DAY;
    }

    // 평일인지 확인 (일~목)
    public boolean isWeekday(LocalDate reserveDate) {
        return switch (reserveDate.getDayOfWeek()) {
            case SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY -> true;
            default -> false;
        };
    }

    // 주말인지 확인 (금~토)
    public boolean isWeekend(LocalDate reserveDate) {
        return switch (reserveDate.getDayOfWeek()) {
            case FRIDAY, SATURDAY -> true;
            default -> false;
        };
    }

    // 일요일 또는 크리스마스인지 확인
    public boolean isSpecialDay(LocalDate reserveDate) {
        return reserveDate.getDayOfWeek() == DayOfWeek.SUNDAY || reserveDate.equals(christmas);
    }
}
