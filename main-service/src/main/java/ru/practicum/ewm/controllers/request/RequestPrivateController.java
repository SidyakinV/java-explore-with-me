package ru.practicum.ewm.controllers.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.request.ParticipationRequestDto;

@RestController
@RequestMapping(path = "/users/{userid}/requests")
@Slf4j
public class RequestPrivateController {

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void /* List<ParticipationRequestDto> */ getUserRequests(
            @PathVariable Long userId
    ) {

    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void /* ParticipationRequestDto */ addEventRequest(
            @PathVariable Long userId,
            @RequestParam Long eventId
    ) {

    }

    @PatchMapping("/{requestId}/cancel")
    @ResponseStatus(value = HttpStatus.OK)
    public void /* ParticipationRequestDto */ cancelEventRequest(
            @PathVariable Long userId,
            @PathVariable Long requestId
    ) {

    }

}
