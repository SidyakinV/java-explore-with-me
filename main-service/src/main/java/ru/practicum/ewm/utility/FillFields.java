package ru.practicum.ewm.utility;

import java.time.LocalDateTime;

public class FillFields {

    public static String ifNull(String newValue, String oldValue) {
        return (newValue == null) ? oldValue : newValue;
    }

    public static Boolean ifNull(Boolean newValue, Boolean oldValue) {
        return (newValue == null) ? oldValue : newValue;
    }

    public static LocalDateTime ifNull(LocalDateTime newValue, LocalDateTime oldValue) {
        return (newValue == null) ? oldValue : newValue;
    }

    public static Float ifNull(Float newValue, Float oldValue) {
        return (newValue == null) ? oldValue : newValue;
    }

    public static Integer ifNull(Integer newValue, Integer oldValue) {
        return (newValue == null) ? oldValue : newValue;
    }

    public static Long ifNull(Long newValue, Long oldValue) {
        return (newValue == null) ? oldValue : newValue;
    }

}
