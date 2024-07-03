package ru.practicum.ewm.exceptions;

import lombok.Getter;
import ru.practicum.ewm.dto.api.ApiError;

public class ConflictException extends RuntimeException {

    @Getter
    private final ApiError apiError;

    public ConflictException(final String message) {
        super(message);
        apiError = new ApiError();
        apiError.setMessage(message);
        apiError.setReason("For the requested operation the conditions are not met");
        apiError.setStatus("409 CONFLICT");

    }
}
