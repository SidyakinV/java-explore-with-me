package ru.practicum.ewm.exceptions;

import lombok.Getter;

public class ValidationException extends RuntimeException {

    @Getter
    private final ApiError apiError;

    public ValidationException(final String message) {
        super(message);
        apiError = new ApiError();
        apiError.setMessage(message);
        apiError.setReason("Incorrectly made request");
        apiError.setStatus("400 BAD_REQUEST");
    }

}
