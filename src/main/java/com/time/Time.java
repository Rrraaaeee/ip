package com.time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

public class Time {

    protected TimeUnit tstart;
    protected TimeUnit tend;
    protected TimeUnit trepeat;
    protected String description;

    /**
     * Constructor
     */
    public Time() {
        tstart = null;
        tend = null;
        trepeat = null;
        description = "";
    }

    /**
     * Constructor
     */
    public Time(String description) {
        this();
        this.description = description;
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

    public TimeUnit getTimeStart(String timeStr) {
        return tstart;
    }

    public TimeUnit getTimeEnd(String timeStr) {
        return tend;
    }

    public TimeUnit getTimeRepeat(String timeStr) {
        return trepeat;
    }

    /**
     * Utility to print time info, used by list command
     */
    public String toString() {
        String s = new String("");
        s += description;
        s += " | Time Start: ";
        if (tstart == null) {
            s += "-1";
        } else {
            s += tstart.get().toString();
        }
        s += " | Time End: ";
        if (tend == null) {
            s += "-1";
        } else {
            s += tend.get().toString();
        }
        s += " | Time Repeat: ";
        if (trepeat == null) {
            s += "-1";
        } else {
            s += trepeat.get().toString();
        }
        s += "\n";
        return s;
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
