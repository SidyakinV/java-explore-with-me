package ru.practicum.ewm.controllers.event;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.event.NewEventDto;
import ru.practicum.ewm.dto.request.UpdateEventUserRequest;
import ru.practicum.ewm.dto.request.EventRequestStatusUpdateRequest;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/users/{userId}/events")
public class EventPrivateController {

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    private void /*List<EventFullDto>*/ getAllEvents(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") Integer from,
            @RequestParam(defaultValue = "10") Integer size
    ) {

    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    private void /*EventFullDto*/ addEvent(
            @PathVariable Long userId,
            @RequestBody @Valid NewEventDto dto
    ) {

    }

    @GetMapping("/{eventId}")
    @ResponseStatus(value = HttpStatus.OK)
    private void /*EventFullDto*/ getEvent(
            @PathVariable Long userId,
            @PathVariable Long eventId
    ) {

    }

    @PatchMapping("/{eventId}")
    @ResponseStatus(value = HttpStatus.OK)
    private void /*EventFullDto*/ updateEvent(
            @PathVariable Long userId,
            @PathVariable Long eventId,
            @RequestBody @Valid UpdateEventUserRequest dto
    ) {

    }

    @GetMapping("/{eventId}/requests")
    @ResponseStatus(value = HttpStatus.OK)
    private void /*List<ParticipationRequestDto>*/ getEventRequests(
            @PathVariable Long userId,
            @PathVariable Long eventId
    ) {

    }

    @PatchMapping("/{eventId}/requests")
    @ResponseStatus(value = HttpStatus.OK)
    private void /*EventRequestStatusUpdateResult*/ changeEventRequestsStatus(
            @PathVariable Long userId,
            @PathVariable Long eventId,
            @RequestBody EventRequestStatusUpdateRequest dto
    ) {

    }

}
