package ru.practicum.ewm.services.event;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.practicum.ewm.dto.event.EventFullDto;
import ru.practicum.ewm.dto.event.EventShortDto;
import ru.practicum.ewm.dto.event.NewEventDto;
import ru.practicum.ewm.dto.event.UpdateEventAdminRequest;
import ru.practicum.ewm.dto.request.EventRequestStatusUpdateRequest;
import ru.practicum.ewm.dto.request.EventRequestStatusUpdateResult;
import ru.practicum.ewm.dto.request.ParticipationRequestDto;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

public interface EventService {

    EventFullDto addEvent(Long userId, NewEventDto dto);

    List<EventShortDto> getAllEvents(Long userId, Pageable pageable);

    EventFullDto getEvent(Long eventId);

    EventFullDto getEvent(Long userId, Long eventId);

    EventFullDto updateEvent(Long userId, Long eventId, UpdateEventAdminRequest dto);

    List<ParticipationRequestDto> getEventRequests(Long userId, Long eventId);

    EventRequestStatusUpdateResult changeEventRequestsStatus(
            Long userId, Long eventId, EventRequestStatusUpdateRequest dto);

    List<EventShortDto> getEventsList(
            String text, List<Integer> categories, Boolean paid,
            LocalDateTime rangeStart, LocalDateTime rangeEnd,
            Boolean onlyAvailable, String sort, Pageable pageable);
}
