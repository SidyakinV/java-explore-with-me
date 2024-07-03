package ru.practicum.ewm.enums.event;

import ru.practicum.ewm.exceptions.UnsupportedException;

public enum EventState {

    PENDING, PUBLISHED, CANCELED;

    public static EventState stringToEventState(String value) {
        try {
            return valueOf(value);
        } catch (Exception e) {
            throw new UnsupportedException(String.format("Unknown event state: %s", value));
        }
    }

}
