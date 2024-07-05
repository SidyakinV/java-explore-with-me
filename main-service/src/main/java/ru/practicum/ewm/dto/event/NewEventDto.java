package ru.practicum.ewm.dto.event;

import lombok.Data;
import ru.practicum.ewm.dto.location.Location;

import javax.validation.constraints.*;

@Data
public class NewEventDto {

    @NotNull
    @NotBlank
    @Size(min = 20, max = 2000)
    private String annotation;

    @NotNull
    private Long category;

    @NotNull
    @NotBlank
    @Size(min = 20, max = 7000)
    private String description;

    @NotNull
    private String eventDate; // Используем тип String, как указано в спецификации

    @NotNull
    private Location location;

    @NotNull
    private Boolean paid;

    @NotNull
    @PositiveOrZero
    private Integer participantLimit;

    @NotNull
    private Boolean requestModeration;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 120)
    private String title;

}
