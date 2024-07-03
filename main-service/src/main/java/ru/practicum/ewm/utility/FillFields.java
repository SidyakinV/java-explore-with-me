package ru.practicum.ewm.utility;

public class FillFields {

    public static String ifNull(String newValue, String oldValue) {
        return (newValue == null) ? oldValue : newValue;
    }

    public static Boolean ifNull(Boolean newValue, Boolean oldValue) {
        return (newValue == null) ? oldValue : newValue;
    }

}
