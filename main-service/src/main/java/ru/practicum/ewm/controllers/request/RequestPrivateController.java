package ru.practicum.ewm.controllers.request;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.request.ParticipationRequestDto;
import ru.practicum.ewm.services.RequestService;

import java.util.List;

@RestController
@RequestMapping(path = "/users/{userId}/requests")
@RequiredArgsConstructor
@Slf4j
public class RequestPrivateController {

    private final RequestService requestService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<ParticipationRequestDto> getUserRequests(
            @PathVariable Long userId
    ) {
        log.info("GET-request '/users/{}/requests'", userId);
        return requestService.getUserRequests(userId);
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public ParticipationRequestDto addEventRequest(
            @PathVariable Long userId,
            @RequestParam Long eventId
    ) {
        log.info("POST-request '/users/{}/requests'", userId);
        return requestService.addEventRequest(userId, eventId);
    }

    @PatchMapping("/{requestId}/cancel")
    @ResponseStatus(value = HttpStatus.OK)
    public ParticipationRequestDto cancelEventRequest(
            @PathVariable Long userId,
            @PathVariable Long requestId
    ) {
        log.info("PATCH-request '/users/{}/requests/{}/cancel'", userId, requestId);
        return requestService.cancelEventRequest(userId, requestId);
    }

}
