package ru.practicum.ewm.mappers;

import ru.practicum.ewm.dto.event.*;
import ru.practicum.ewm.dto.location.Location;
import ru.practicum.ewm.models.Event;

import static ru.practicum.ewm.utility.DateTimeFormat.*;
import static ru.practicum.ewm.utility.FillFields.*;

import java.time.LocalDateTime;

public class EventMapper {

    public static Event mapNewEventDtoToEvent(NewEventDto dto) {
        Event event = new Event();
        event.setAnnotation(dto.getAnnotation());
        event.setDescription(dto.getDescription());
        event.setEventDate(stringToDateTime(dto.getEventDate()));
        event.setLocationLat(dto.getLocation().getLat());
        event.setLocationLon(dto.getLocation().getLon());
        event.setPaid(ifNull(dto.getPaid(), false));
        event.setParticipantLimit(ifNull(dto.getParticipantLimit(), 0));
        event.setRequestModeration(ifNull(dto.getRequestModeration(),true));
        event.setTitle(dto.getTitle());
        event.setCreated(LocalDateTime.now());
        return event;
    }

    public static Event mapUpdateUserEventDtoToEvent(UpdateEventUserRequest dto, Event oldEvent) {
        Event event = new Event();
        event.setAnnotation(ifNull(dto.getAnnotation(), oldEvent.getAnnotation()));
        event.setDescription(ifNull(dto.getDescription(), oldEvent.getDescription()));
        event.setEventDate(ifNull(stringToDateTime(dto.getEventDate()), oldEvent.getEventDate()));
        event.setPaid(ifNull(dto.getPaid(), oldEvent.getPaid()));
        event.setParticipantLimit(ifNull(dto.getParticipantLimit(), oldEvent.getParticipantLimit()));
        event.setRequestModeration(ifNull(dto.getRequestModeration(), oldEvent.getRequestModeration()));
        event.setTitle(ifNull(dto.getTitle(), oldEvent.getTitle()));

        event.setCategory(oldEvent.getCategory());
        event.setState(oldEvent.getState());
        event.setConfirmedRequests(oldEvent.getConfirmedRequests());
        event.setCreated(oldEvent.getCreated());
        event.setPublished(oldEvent.getPublished());
        event.setViews(oldEvent.getViews());
        event.setInitiator(oldEvent.getInitiator());

        if (dto.getLocation() == null) {
            event.setLocationLat(oldEvent.getLocationLat());
            event.setLocationLon(oldEvent.getLocationLon());
        } else {
            event.setLocationLat(ifNull(dto.getLocation().getLat(), oldEvent.getLocationLat()));
            event.setLocationLon(ifNull(dto.getLocation().getLon(), oldEvent.getLocationLon()));
        }

        return event;
    }

    public static Event mapUpdateEventAdminDtoToEvent(UpdateEventAdminRequest dto, Event oldEvent) {
        Event event = new Event();
        event.setAnnotation(ifNull(dto.getAnnotation(), oldEvent.getAnnotation()));
        event.setDescription(ifNull(dto.getDescription(), oldEvent.getDescription()));
        event.setEventDate(ifNull(stringToDateTime(dto.getEventDate()), oldEvent.getEventDate()));
        event.setPaid(ifNull(dto.getPaid(), oldEvent.getPaid()));
        event.setParticipantLimit(ifNull(dto.getParticipantLimit(), oldEvent.getParticipantLimit()));
        event.setRequestModeration(ifNull(dto.getRequestModeration(), oldEvent.getRequestModeration()));
        event.setTitle(ifNull(dto.getTitle(), oldEvent.getTitle()));

        event.setId(oldEvent.getId());
        event.setCategory(oldEvent.getCategory());
        event.setState(oldEvent.getState());
        event.setConfirmedRequests(oldEvent.getConfirmedRequests());
        event.setCreated(oldEvent.getCreated());
        event.setPublished(oldEvent.getPublished());
        event.setViews(oldEvent.getViews());
        event.setInitiator(oldEvent.getInitiator());

        if (dto.getLocation() == null) {
            event.setLocationLat(oldEvent.getLocationLat());
            event.setLocationLon(oldEvent.getLocationLon());
        } else {
            event.setLocationLat(ifNull(dto.getLocation().getLat(), oldEvent.getLocationLat()));
            event.setLocationLon(ifNull(dto.getLocation().getLon(), oldEvent.getLocationLon()));
        }

        return event;
    }

    public static EventFullDto mapEventToEventFullDto(Event event) {
        EventFullDto dto = new EventFullDto();
        dto.setLocation(new Location());

        dto.setAnnotation(event.getAnnotation());
        dto.setCategory(CategoryMapper.mapCategoryToDto(event.getCategory()));
        dto.setConfirmedRequests(event.getConfirmedRequests());
        dto.setCreatedOn(dateTimeToString(event.getCreated()));
        dto.setEventDate(dateTimeToString(event.getEventDate()));
        dto.setDescription(event.getDescription());
        dto.setId(event.getId());
        dto.setInitiator(UserMapper.mapUserToUserShortDto(event.getInitiator()));
        dto.getLocation().setLat(event.getLocationLat());
        dto.getLocation().setLon(event.getLocationLon());
        dto.setPaid(event.getPaid());
        dto.setParticipantLimit(event.getParticipantLimit());
        dto.setPublishedOn(dateTimeToString(event.getPublished()));
        dto.setRequestModeration(event.getRequestModeration());
        dto.setState(event.getState().toString());
        dto.setTitle(event.getTitle());
        dto.setViews(event.getViews());
        dto.setRating(event.getRating());
        return dto;
    }

    public static EventShortDto mapEventToEventShortDto(Event event) {
        EventShortDto dto = new EventShortDto();
        dto.setAnnotation(event.getAnnotation());
        dto.setCategory(CategoryMapper.mapCategoryToDto(event.getCategory()));
        dto.setConfirmedRequests(event.getConfirmedRequests());
        dto.setEventDate(dateTimeToString(event.getEventDate()));
        dto.setId(event.getId());
        dto.setInitiator(UserMapper.mapUserToUserShortDto(event.getInitiator()));
        dto.setPaid(event.getPaid());
        dto.setTitle(event.getTitle());
        dto.setViews(event.getViews());
        dto.setRating(event.getRating());
        return dto;
    }

}
