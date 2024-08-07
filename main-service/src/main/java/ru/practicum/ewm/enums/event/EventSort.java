package ru.practicum.ewm.enums.event;

import ru.practicum.ewm.exceptions.UnsupportedException;

public enum EventSort {

    EVENT_DATE, VIEWS;

    public static EventSort stringToEventSort(String value) {
        try {
            return valueOf(value);
        } catch (Exception e) {
            throw new UnsupportedException(String.format("Unknown event sort: %s", value));
        }
    }

}
