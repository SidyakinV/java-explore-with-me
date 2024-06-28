package ru.practicum.ewm.dto.event;

import lombok.Data;
import ru.practicum.ewm.dto.location.Location;

import javax.validation.constraints.Size;

@Data
public class NewEventDto {

    @Size(min = 3, max = 120)
    private String title;

    @Size(min = 20, max = 2000)
    private String annotation;

    @Size(min = 20, max = 7000)
    private String description;

    private Long category;

    private String eventDate; // Используем тип String, как указано в спецификации

    private Location location;

    private Boolean paid;

    private Integer participantLimit;

    private Boolean requestModeration;

}