package ru.practicum.ewm.controllers.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.event.EventFullDto;
import ru.practicum.ewm.dto.event.UpdateEventAdminRequest;
import ru.practicum.ewm.services.EventService;
import ru.practicum.ewm.utility.DateTimeFormat;
import ru.practicum.ewm.utility.PageCalc;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/admin/events")
@RequiredArgsConstructor
@Slf4j
public class EventAdminController {

    private final EventService eventService;

    @GetMapping
    public List<EventFullDto> getAllEvents(
            @RequestParam(required = false) List<Long> users,
            @RequestParam(required = false) List<String> states,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) String rangeStart,
            @RequestParam(required = false) String rangeEnd,
            @RequestParam(defaultValue = "0") Integer from,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        log.info("GET-request '/admin/events' with parameters: " +
                    "users {}, states {}, categories {}, rangeStart {}, rangeEnd {}, from {}, size {}",
                    users, states, categories, rangeStart, rangeEnd, from, size);
        return eventService.getAdminEvents(
                users, states, categories,
                DateTimeFormat.stringToDateTime(rangeStart),
                DateTimeFormat.stringToDateTime(rangeEnd),
                PageCalc.getPageable(from, size));
    }

    @PatchMapping("/{eventId}")
    public EventFullDto updateEvent(
            @PathVariable Long eventId,
            @RequestBody @Valid UpdateEventAdminRequest dto
    ) {
        log.info("PATCH-request '/admin/events/{}' with parameters: dto {}", eventId, dto);
        return eventService.updateEvent(eventId, dto);
    }


}
