package ru.kami.model.api;

import lombok.Getter;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static ru.kami.constant.ChatTimeConstants.*;

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
     * @param string строка времени в формате: year:month:dayOfMonth:hour:minute:second
     * @return экземляр класса созданной по переданной строке
     */
    public static ChatTime valueOf(String string) throws NumberFormatException {
        String[] split = string.split(":");
        return new ChatTime(Integer.parseInt(split[YEAR_INDEX]),
                Integer.parseInt(split[MONTH_INDEX]),
                Integer.parseInt(split[WEEK_OF_MONTH_INDEX]),
                Integer.parseInt(split[HOUR_INDEX]),
                Integer.parseInt(split[MINUTE_INDEX]),
                Integer.parseInt(split[SECOND_INDEX]));
    }

    /**
     * @param chatTime время
     * @return разницу с переданым экземляром класса в часах
     */
    public int calculateDifferenceHours(ChatTime chatTime) {
        Calendar thisCalendar = getGregorianCalendar();
        Calendar calendar = chatTime.getGregorianCalendar();
        return (int) ((thisCalendar.getTimeInMillis() - calendar.getTimeInMillis()) / MILLISECONDS_PER_HOUR);

    }

    /**
     * @return GregorianCalendar этого экземляра класса
     */
    private GregorianCalendar getGregorianCalendar() {
        return new GregorianCalendar(year, month, dayOfMonth, hour, minute, second);
    }
}
