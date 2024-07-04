package ru.practicum.ewm.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormat {

    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public static String dateTimeToString(LocalDateTime dateTime) {
        return dateTime.format(dateTimeFormatter);
    }

    public static LocalDateTime stringToDateTime(String str) {
        return LocalDateTime.parse(str, dateTimeFormatter);
    }

}
