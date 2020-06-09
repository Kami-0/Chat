package ru.kami.api;

import lombok.Getter;

import java.util.Calendar;

/**
 * Класс для передачи даты и времени в чате
 */
@Getter
public class ChatTime {
    private int year;
    private int month;
    private int dayOfMonth;
    private int hour;
    private int minute;
    private int second;

    private ChatTime(int year, int month, int dayOfMonth, int hour, int minute, int second) {
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    /**
     * @return возращает инстанс текущего времени
     */
    public static ChatTime getInstance() {
        Calendar calendar = Calendar.getInstance();
        return new ChatTime(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.WEEK_OF_MONTH),
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                calendar.get(Calendar.SECOND));
    }

    /**
     * @return строковое представление экземляра класса в формате: year:month:dayOfMonth:hour:minute:second
     */
    public String convertToString() {
        return year + ":" + month + ":" + dayOfMonth + ":" + hour + ":" + minute + ":" + second;
    }

    /**
     * @return часы и минуты в формате hour:minute
     */
    public String getHourAndMinute() {
        return hour + ":" + minute;
    }
}
