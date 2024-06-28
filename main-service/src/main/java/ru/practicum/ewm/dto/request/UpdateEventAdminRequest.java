package ru.practicum.ewm.dto.request;

import lombok.Data;
import ru.practicum.ewm.dto.location.Location;

import javax.validation.constraints.Size;

@Data
public class UpdateEventAdminRequest {

    @Size(min = 20, max = 2000)
    private String annotation;

    private Long category;

    @Size(min = 20, max = 7000)
    private String description;

    private String eventDate; // Тип String как в спецификации

    private Location location;

    private Boolean paid;

    private Integer participantLimit;

    private Boolean requestModeration;

    private String stateAction;

    @Size(min = 3, max = 120)
    private String title;

}
