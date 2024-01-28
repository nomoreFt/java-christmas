package christmas.v1;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class EventCalendar {

    private LocalDate eventStartDate;
    private LocalDate eventEndDate;

    public EventCalendar(LocalDate eventStartDate, LocalDate eventEndDate) {
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
    }

    public LocalDate getEventStartDate() {
        return eventStartDate;
    }


    public boolean isBeforeChristmas(LocalDate date) {
        return !date.isBefore(eventStartDate) && !date.isAfter(eventEndDate);
    }

    //일요일 ~ 목요일인지 확인
    public boolean isWeekday(LocalDate orderedDate) {
        DayOfWeek day = orderedDate.getDayOfWeek();
        return day == DayOfWeek.SUNDAY || day.getValue() <= DayOfWeek.THURSDAY.getValue();
    }


    public boolean isWeekend(LocalDate orderedDate) {
        DayOfWeek day = orderedDate.getDayOfWeek();
        return day == DayOfWeek.FRIDAY || day == DayOfWeek.SATURDAY;
    }

    public boolean isSpecialDay(LocalDate orderedDate) {
        DayOfWeek dayOfWeek = orderedDate.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SUNDAY || isChriestmas(orderedDate);
    }

    private boolean isChriestmas(LocalDate orderedDate) {
        return orderedDate.getMonthValue() == 12 && orderedDate.getDayOfMonth() == 25;
    }
}