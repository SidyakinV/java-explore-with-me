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

    EventFullDto getEvent(Long eventId, String ip, String path);

    EventFullDto getEvent(Long userId, Long eventId);

    EventFullDto updateEvent(Long eventId, UpdateEventAdminRequest dto);

    EventFullDto updateEvent(Long userId, Long eventId, UpdateEventUserRequest dto);

    List<ParticipationRequestDto> getEventRequests(Long userId, Long eventId);

    EventRequestStatusUpdateResult changeEventRequestsStatus(
            Long userId, Long eventId, EventRequestStatusUpdateRequest dto);

    List<EventShortDto> getUserEvents(Long userId, Pageable pageable);

    List<EventFullDto> getAdminEvents(
            List<Long> userList, List<String> stateList, List<Long> categoryList,
            LocalDateTime rangeStart, LocalDateTime rangeEnd, Pageable pageable);

    List<EventShortDto> getPublicEvents(
            String searchText, List<Long> categoryList, Boolean paid,
            LocalDateTime rangeStart, LocalDateTime rangeEnd,
            Boolean onlyAvailable, String sortBy, Integer from, Integer size,
            String ip, String path);

}
