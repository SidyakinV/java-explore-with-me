package ru.practicum.ewm.enums.request;

import ru.practicum.ewm.enums.event.EventState;
import ru.practicum.ewm.exceptions.UnsupportedException;

public enum RequestStatus {

    PENDING, CONFIRMED, REJECTED;

    public static RequestStatus stringToRequestStatus(String value) {
        try {
            return valueOf(value);
        } catch (Exception e) {
            throw new UnsupportedException(String.format("Unknown request status: %s", value));
        }
    }

}
