package com.time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

public class Time {

    private LocalDateTime time;

    public Time() {
        this.time = null;
    }

    public Time(LocalDateTime localTime) {
        this.time = localTime;
    }

    /**
     * toString method to make Time class printable
     */
    public String toString() {
        if (time != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-L-dd HH:mm");
            return time.format(formatter);
        } else {
            return "INVALID TIME\n";
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
