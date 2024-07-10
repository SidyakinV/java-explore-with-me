package ru.practicum.ewm.controllers.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/events/{eventId}/rating")
@RequiredArgsConstructor
@Slf4j
public class EventRatingController {

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addEventRating() {

    }

    @PatchMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void updateEventRating() {

    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteEventRating() {

    }

}
