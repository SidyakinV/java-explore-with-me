package ru.practicum.ewm.exceptions;

import lombok.Getter;
import ru.practicum.ewm.dto.api.ApiError;

public class UnsupportedException extends RuntimeException {

    @Getter
    private final ApiError apiError;

    public UnsupportedException(final String message) {
        super(message);
        apiError = new ApiError();
        apiError.setMessage(message);
        apiError.setReason("Incorrectly made request");
        apiError.setStatus("400 BAD_REQUEST");
    }
}
