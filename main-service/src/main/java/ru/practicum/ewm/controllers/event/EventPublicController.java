package ru.practicum.ewm.controllers.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.event.EventFullDto;
import ru.practicum.ewm.dto.event.EventShortDto;
import ru.practicum.ewm.services.event.EventService;
import ru.practicum.ewm.utility.DateTimeFormat;
import ru.practicum.ewm.utility.PageCalc;

import java.util.List;

@RestController
@RequestMapping(path = "/events")
@RequiredArgsConstructor
@Slf4j
public class EventPublicController {

    private final EventService eventService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<EventShortDto> getEventsList(
            @RequestParam(defaultValue = "") String text,
            @RequestParam(required = false) List<Integer> categories,
            @RequestParam Boolean paid,
            @RequestParam(required = false) String rangeStart,
            @RequestParam(required = false) String rangeEnd,
            @RequestParam(defaultValue = "false") Boolean onlyAvailable,
            @RequestParam String sort,
            @RequestParam(defaultValue = "0") Integer from,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        log.info("GET-request '/events' with parameters: " +
                "text {}, categories {}, paid {}, rangeStart {}, rangeEnd {}, " +
                "onlyAvailable {}, sort {}, from {}, size {}",
                text, categories, paid, rangeStart, rangeEnd,
                onlyAvailable, sort, from, size);
        return eventService.getEventsList(
                text, categories, paid,
                DateTimeFormat.stringToDateTime(rangeStart),
                DateTimeFormat.stringToDateTime(rangeEnd),
                onlyAvailable, sort, PageCalc.getPageable(from, size));
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public EventFullDto getEvent(
            @PathVariable Long id
    ) {
        log.info("GET-request '/events/{}'", id);
        return eventService.getEvent(id);
    }

}
