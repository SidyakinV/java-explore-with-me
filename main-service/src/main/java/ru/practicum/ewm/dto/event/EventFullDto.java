package ru.practicum.ewm.dto.event;

import lombok.Data;
import ru.practicum.ewm.dto.category.CategoryDto;
import ru.practicum.ewm.dto.location.Location;
import ru.practicum.ewm.dto.user.UserShortDto;

@Data
public class EventFullDto {

    private String annotation;

    private CategoryDto category;

    private Long confirmedRequests;

    private String createdOn; // Используем тип String, как указано в спецификации

    private String eventDate; // Аналогично

    private String description;

    private Long id;

    private UserShortDto initiator;

    private Location location;

    private Boolean paid;

    private Integer participantLimit;

    private String publishedOn; // Тоже дата, и тоже String

    private Boolean requestModeration;

    private String state;

    private String title;

    private Long views;

    private Long rating;

}