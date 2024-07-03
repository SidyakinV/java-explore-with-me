package ru.practicum.ewm.controllers.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.event.*;
import ru.practicum.ewm.dto.request.EventRequestStatusUpdateRequest;
import ru.practicum.ewm.dto.request.EventRequestStatusUpdateResult;
import ru.practicum.ewm.dto.request.ParticipationRequestDto;
import ru.practicum.ewm.services.event.EventService;
import ru.practicum.ewm.utility.PageCalc;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/users/{userId}/events")
@RequiredArgsConstructor
@Slf4j
public class EventPrivateController {

    private final EventService eventService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<EventShortDto> getAllEvents(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") Integer from,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        log.info("GET-request '/users/{}/events' with parameters: from {}, size{}", userId, from, size);
        return eventService.getUserEvents(userId, PageCalc.getPageable(from, size));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public EventFullDto addEvent(
            @PathVariable Long userId,
            @RequestBody @Valid NewEventDto dto
    ) {
        log.info("POST-request '/users/{}/events' with parameters: dto {}", userId, dto);
        return eventService.addEvent(userId, dto);
    }

    @GetMapping("/{eventId}")
    @ResponseStatus(value = HttpStatus.OK)
    public EventFullDto getEvent(
            @PathVariable Long userId,
            @PathVariable Long eventId
    ) {
        log.info("GET-request '/users/{}/events/{}'", userId, eventId);
        return eventService.getEvent(userId, eventId);
    }

    @PatchMapping("/{eventId}")
    @ResponseStatus(value = HttpStatus.OK)
    public EventFullDto updateEvent(
            @PathVariable Long userId,
            @PathVariable Long eventId,
            @RequestBody @Valid UpdateEventUserRequest dto
    ) {
        log.info("PATCH-request '/users/{}/events/{}'", userId, eventId);
        return eventService.updateEvent(userId, eventId, dto);
    }

    @GetMapping("/{eventId}/requests")
    @ResponseStatus(value = HttpStatus.OK)
    public List<ParticipationRequestDto> getEventRequests(
            @PathVariable Long userId,
            @PathVariable Long eventId
    ) {
        log.info("GET-request '/users/{}/events/{}/requests'", userId, eventId);
        return eventService.getEventRequests(userId, eventId);
    }

    @PatchMapping("/{eventId}/requests")
    @ResponseStatus(value = HttpStatus.OK)
    private EventRequestStatusUpdateResult changeEventRequestsStatus(
            @PathVariable Long userId,
            @PathVariable Long eventId,
            @RequestBody EventRequestStatusUpdateRequest dto
    ) {
        log.info("PATCH-request '/users/{}/events/{}/requests'", userId, eventId);
        return eventService.changeEventRequestsStatus(userId, eventId, dto);
    }

}
