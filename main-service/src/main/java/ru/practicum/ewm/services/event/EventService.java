package ru.practicum.ewm.services.event;

import org.springframework.data.domain.Pageable;
import ru.practicum.ewm.dto.event.*;
import ru.practicum.ewm.dto.request.EventRequestStatusUpdateRequest;
import ru.practicum.ewm.dto.request.EventRequestStatusUpdateResult;
import ru.practicum.ewm.dto.request.ParticipationRequestDto;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {

    EventFullDto addEvent(Long userId, NewEventDto dto);

    List<EventShortDto> getUserEvents(Long userId, Pageable pageable);

    EventFullDto getEvent(Long eventId);

    EventFullDto getEvent(Long userId, Long eventId);

    EventFullDto updateEvent(Long eventId, UpdateEventAdminRequest dto);

    EventFullDto updateEvent(Long userId, Long eventId, UpdateEventUserRequest dto);

    List<ParticipationRequestDto> getEventRequests(Long userId, Long eventId);

    EventRequestStatusUpdateResult changeEventRequestsStatus(
            Long userId, Long eventId, EventRequestStatusUpdateRequest dto);

    List<EventShortDto> getEventsList(
            String text, List<Integer> categories, Boolean paid,
            LocalDateTime rangeStart, LocalDateTime rangeEnd,
            Boolean onlyAvailable, String sort, Pageable pageable);

    List<EventFullDto> getUserEvents(
            List<Long> users, List<String> states, List<Long> categories,
            LocalDateTime rangeStart, LocalDateTime rangeEnd, Pageable pageable);

}
