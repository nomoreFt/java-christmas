package christmas.v1.order.policy.rule.calculator;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class EventCalendar {

    private LocalDate eventStartDate;
    private LocalDate eventEndDate;

    private EventCalendar(LocalDate eventStartDate, LocalDate eventEndDate) {
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
    }

    //of
    public static EventCalendar of(LocalDate eventStartDate, LocalDate eventEndDate) {
        return new EventCalendar(eventStartDate, eventEndDate);
    }

    public LocalDate getEventStartDate() {
        return eventStartDate;
    }


    public boolean isBeforeChristmas(LocalDate date) {
        //1일 ~ 25인지 확인
        return date.getMonthValue() == 12 && date.getDayOfMonth() <= 25;
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