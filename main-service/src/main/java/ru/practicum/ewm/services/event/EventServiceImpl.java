package ru.practicum.ewm.services.event;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.dto.event.EventFullDto;
import ru.practicum.ewm.dto.event.EventShortDto;
import ru.practicum.ewm.dto.event.NewEventDto;
import ru.practicum.ewm.dto.event.UpdateEventAdminRequest;
import ru.practicum.ewm.dto.request.EventRequestStatusUpdateRequest;
import ru.practicum.ewm.dto.request.EventRequestStatusUpdateResult;
import ru.practicum.ewm.dto.request.ParticipationRequestDto;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Override
    public EventFullDto addEvent(Long userId, NewEventDto dto) {
        return null;
    }

    @Override
    public List<EventShortDto> getAllEvents(Long userId, Pageable pageable) {
        return null;
    }

    @Override
    public EventFullDto getEvent(Long eventId) {
        return null;
    }

    @Override
    public EventFullDto getEvent(Long userId, Long eventId) {
        return null;
    }

    @Override
    public EventFullDto updateEvent(Long userId, Long eventId, UpdateEventAdminRequest dto) {
        return null;
    }

    @Override
    public List<ParticipationRequestDto> getEventRequests(Long userId, Long eventId) {
        return null;
    }

    @Override
    public EventRequestStatusUpdateResult changeEventRequestsStatus(Long userId, Long eventId, EventRequestStatusUpdateRequest dto) {
        return null;
    }

    @Override
    public List<EventShortDto> getEventsList(String text, List<Integer> categories, Boolean paid, LocalDateTime rangeStart, LocalDateTime rangeEnd, Boolean onlyAvailable, String sort, Pageable pageable) {
        return null;
    }

}
