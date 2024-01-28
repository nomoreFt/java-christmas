package christmas.v0.event;

import java.time.DayOfWeek;
import java.time.LocalDate;

//싱글빈 객체로 만들어서 사용
public class EventCalendar {

    private LocalDate eventStartDate;
    private LocalDate eventEndDate;

    public EventCalendar(LocalDate eventStartDate, LocalDate eventEndDate) {
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
    }


}
