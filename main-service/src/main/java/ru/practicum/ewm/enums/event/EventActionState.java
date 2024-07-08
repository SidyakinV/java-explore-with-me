package ru.practicum.ewm.enums.event;

import ru.practicum.ewm.exceptions.UnsupportedException;

public enum EventActionState {

    PUBLISH_EVENT, REJECT_EVENT, SEND_TO_REVIEW, CANCEL_REVIEW;

    public static EventActionState stringToEventActionState(String value) {
        try {
            return valueOf(value);
        } catch (Exception e) {
            throw new UnsupportedException(String.format("Unknown event state action: %s", value));
        }
    }

}
