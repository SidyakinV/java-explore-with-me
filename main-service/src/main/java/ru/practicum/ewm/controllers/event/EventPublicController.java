package ru.practicum.ewm.controllers.event;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/events")
public class EventPublicController {

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void /*List<EventFullDto>*/ getEventsList(
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

    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void /*EventFullDto*/ getEvent(
            @PathVariable Long id
    ) {

    }

}
