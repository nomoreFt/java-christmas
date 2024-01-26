package christmas.v1;

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
}