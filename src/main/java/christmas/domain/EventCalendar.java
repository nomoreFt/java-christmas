package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;


public enum EventCalendar {
    DAY1(1), DAY2(2), DAY3(3),
    DAY4(4), DAY5(5), DAY6(6),
    DAY7(7), DAY8(8), DAY9(9),
    DAY10(10), DAY11(11), DAY12(12),
    DAY13(13), DAY14(14), DAY15(15),
    DAY16(16), DAY17(17), DAY18(18),
    DAY19(19), DAY20(20), DAY21(21),
    DAY22(22), DAY23(23), DAY24(24),
    DAY25(25), DAY26(26), DAY27(27),
    DAY28(28), DAY29(29), DAY30(30),
    DAY31(31),DAYINVALID(0);

    private final int day;

    EventCalendar(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public static EventCalendar of(int reservationDay) {
        if(isDecemberDay(reservationDay)){
            return EventCalendar.valueOf("DAY" + reservationDay);
        }
        return DAYINVALID;
    }

    public boolean hasStar(int year) {
        return isSunday(year) || isChristMas();
    }

    public boolean isChristMas() {
        return this == DAY25;
    }

    public boolean isWeekend(int year) {
        LocalDate date = LocalDate.of(year, 12, day);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SUNDAY
                || dayOfWeek == DayOfWeek.MONDAY
                || dayOfWeek == DayOfWeek.TUESDAY
                || dayOfWeek == DayOfWeek.WEDNESDAY
                || dayOfWeek == DayOfWeek.THURSDAY;
    }

    public boolean isHoliday(int year) {
        LocalDate date = LocalDate.of(year, 12, day);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    public boolean isSunday(int year) {
        LocalDate date = LocalDate.of(year, 12, day);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SUNDAY;
    }

    public boolean isBeforeOrEaqualChristMas() {
        return 1 <= this.day && this.day <= 25;
    }
    public static boolean isDecemberDay(int reservationDate) {
        return reservationDate >= 1 && reservationDate <= 31;
    }
}