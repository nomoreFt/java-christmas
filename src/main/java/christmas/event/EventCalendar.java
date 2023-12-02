package christmas.event;

import java.time.LocalDate;

public abstract class EventCalendar {
    //연도
    public int eventYear;
    //월
    public int eventMonth;
    public LocalDate eventStartDate;
    public LocalDate eventEndDate;


    public EventCalendar(int eventYear, int eventMonth, int eventStartDay, int eventEndDay) {
        this.eventYear = eventYear;
        this.eventMonth = eventMonth;
        this.eventStartDate = LocalDate.of(eventYear, eventMonth, eventStartDay);
        this.eventEndDate = LocalDate.of(eventYear, eventMonth, eventEndDay);
    }



    


}
