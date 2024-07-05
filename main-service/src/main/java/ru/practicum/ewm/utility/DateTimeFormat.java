package ru.practicum.ewm.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormat {

    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String dateTimeToString(LocalDateTime dateTime) {
        return (dateTime == null) ? null : dateTime.format(dateTimeFormatter);
    }

    public static LocalDateTime stringToDateTime(String str) {
        return (str == null) ? null : LocalDateTime.parse(str, dateTimeFormatter);
    }

}
