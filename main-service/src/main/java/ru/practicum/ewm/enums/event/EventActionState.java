package ru.practicum.ewm.enums.event;

import ru.practicum.ewm.exceptions.UnsupportedException;

public enum EventActionState {

    PUBLISH_EVENT, REJECT_EVENT;

    public static EventActionState stringToEventActionState(String value) {
        try {
            return valueOf(value);
        } catch (Exception e) {
            throw new UnsupportedException(String.format("Unknown event state action: %s", value));
        }
    }

}
