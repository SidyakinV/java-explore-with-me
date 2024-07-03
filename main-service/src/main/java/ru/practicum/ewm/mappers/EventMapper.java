package ru.practicum.ewm.mappers;

import ru.practicum.ewm.dto.category.CategoryMapper;
import ru.practicum.ewm.dto.event.*;
import ru.practicum.ewm.dto.location.Location;
import ru.practicum.ewm.dto.user.UserMapper;
import ru.practicum.ewm.models.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventMapper {

    public static Event mapNewEventDtoToEvent(NewEventDto dto) {
        Event event = new Event();
        event.setAnnotation(dto.getAnnotation());
        event.setDescription(dto.getDescription());
        event.setEventDate(LocalDateTime.parse(dto.getEventDate(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        event.setLocationLat(dto.getLocation().getLat());
        event.setLocationLon(dto.getLocation().getLon());
        event.setPaid(dto.getPaid());
        event.setParticipantLimit(dto.getParticipantLimit());
        event.setRequestModeration(dto.getRequestModeration());
        event.setTitle(dto.getTitle());
        event.setCreated(LocalDateTime.now());
        return event;
    }

    public static Event mapUpdateUserEventDtoToEvent(UpdateEventUserRequest dto) {
        Event event = new Event();
        event.setAnnotation(dto.getAnnotation());
        event.setDescription(dto.getDescription());
        event.setEventDate(LocalDateTime.parse(dto.getEventDate(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        event.setLocationLat(dto.getLocation().getLat());
        event.setLocationLon(dto.getLocation().getLon());
        event.setPaid(dto.getPaid());
        event.setParticipantLimit(dto.getParticipantLimit());
        event.setRequestModeration(dto.getRequestModeration());
        event.setTitle(dto.getTitle());
        return event;
    }

    public static Event mapUpdateAdminEventDtoToEvent(UpdateEventAdminRequest dto) {
        Event event = new Event();
        event.setAnnotation(dto.getAnnotation());
        event.setDescription(dto.getDescription());
        event.setEventDate(LocalDateTime.parse(dto.getEventDate(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        event.setLocationLat(dto.getLocation().getLat());
        event.setLocationLon(dto.getLocation().getLon());
        event.setPaid(dto.getPaid());
        event.setParticipantLimit(dto.getParticipantLimit());
        event.setRequestModeration(dto.getRequestModeration());
        event.setTitle(dto.getTitle());
        return event;
    }

    public static EventFullDto mapEventToEventFullDto(Event event) {
        EventFullDto dto = new EventFullDto();
        dto.setAnnotation(event.getAnnotation());
        dto.setCategory(CategoryMapper.mapCategoryToDto(event.getCategory()));
        dto.setConfirmedRequests(event.getConfirmedRequests());
        dto.setCreatedOn(event.getCreated().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        dto.setEventDate(event.getEventDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        dto.setDescription(event.getDescription());
        dto.setId(event.getId());
        dto.setInitiator(UserMapper.mapUserToUserShortDto(event.getInitiator()));
        dto.setLocation(new Location(event.getLocationLat(), event.getLocationLon()));
        dto.setPaid(event.getPaid());
        dto.setParticipantLimit(event.getParticipantLimit());
        dto.setPublishedOn(event.getPublished().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        dto.setRequestModeration(event.getRequestModeration());
        dto.setState(event.getState().toString());
        dto.setTitle(event.getTitle());
        dto.setViews(event.getViews());
        return dto;
    }

    public static EventShortDto mapEventToEventShortDto(Event event) {
        EventShortDto dto = new EventShortDto();
        dto.setAnnotation(event.getAnnotation());
        dto.setCategory(CategoryMapper.mapCategoryToDto(event.getCategory()));
        dto.setConfirmedRequests(event.getConfirmedRequests());
        dto.setEventDate(event.getEventDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        dto.setId(event.getId());
        dto.setInitiator(UserMapper.mapUserToUserShortDto(event.getInitiator()));
        dto.setPaid(event.getPaid());
        dto.setTitle(event.getTitle());
        dto.setViews(event.getViews());
        return dto;
    }

}
