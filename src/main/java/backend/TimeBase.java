package backend;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

public abstract class TimeBase {

    protected TimeUnit tstart;
    protected TimeUnit tend;
    protected TimeUnit trepeat;

    /**
     * Constructor
     */
    public TimeBase() {
        tstart = null;
        tend = null;
        trepeat = null;
    }

    protected void setTimeStart(String timeStr) {
        tstart = new TimeUnit(timeStr);
    }

    protected void setTimeEnd(String timeStr) {
        tend = new TimeUnit(timeStr);
    }

    protected void setTimeRepeat(String timeStr) {
        trepeat = new TimeUnit(timeStr);
    }

    protected TimeUnit getTimeStart(String timeStr) {
        return tstart;
    }

    protected TimeUnit getTimeEnd(String timeStr) {
        return tend;
    }

    protected TimeUnit getTimeRepeat(String timeStr) {
        return trepeat;
    }

    protected void printTimeInfo() {
        System.out.print("++Time Start: ");
        if (tstart == null) {
            System.out.println(-1);
        } else {
            System.out.println(tstart.get());
        }
        System.out.print("++Time End: ");
        if (tend == null) {
            System.out.println(-1);
        } else {
            System.out.println(tend.get());
        }
        System.out.print("++Time Repeat: ");
        if (trepeat == null) {
            System.out.println(-1);
        } else {
            System.out.println(trepeat.get());
        }
    }
}

/*
 * YYYY:MM:dd:HH:mm:ss
 * https://stackoverflow.com/questions/44925840/
 * java-time-format-datetimeparseexception-text-could-not-be-parsed-at-index-3
 * Multiple formats
 */
class TimeUnit {
    private LocalDateTime localTime; // Create a date object

    public TimeUnit() {
        localTime = LocalDateTime.now();
    }

    public TimeUnit(String timeStr) {
        localTime = LocalDateTime.parse(timeStr, DateTimeFormatter.ofPattern("yyyy:MM:dd:HH:mm:ss"));
    }

    public LocalDateTime get() {
        return localTime;
    }

    public int getYear() {
        return localTime.get(ChronoField.YEAR);
    }

    public int getMonth() {
        return localTime.get(ChronoField.MONTH_OF_YEAR);
    }

    public int getDay() {
        return localTime.get(ChronoField.DAY_OF_MONTH);
    }

    public int getHour() {
        return localTime.get(ChronoField.HOUR_OF_DAY);
    }

    public int getMinute() {
        return localTime.get(ChronoField.MINUTE_OF_HOUR);
    }

    public int getSecond() {
        return localTime.get(ChronoField.SECOND_OF_MINUTE);
    }
}
