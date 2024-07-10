package ru.practicum.ewm.controllers.event;

import dto.EndpointHit;
import httpclient.StatsClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.event.EventFullDto;
import ru.practicum.ewm.dto.event.EventShortDto;
import ru.practicum.ewm.services.EventService;
import static ru.practicum.ewm.utility.DateTimeFormat.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/events")
@RequiredArgsConstructor
@Slf4j
public class EventPublicController {

    private final EventService eventService;
    private final StatsClient httpClient;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<EventShortDto> getEventsList(
            @RequestParam(defaultValue = "") String text,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) Boolean paid,
            @RequestParam(required = false) String rangeStart,
            @RequestParam(required = false) String rangeEnd,
            @RequestParam(defaultValue = "false") Boolean onlyAvailable,
            @RequestParam(required = false) String sort,
            @RequestParam(defaultValue = "0") Integer from,
            @RequestParam(defaultValue = "10") Integer size,
            HttpServletRequest request
    ) {
        log.info("GET-request '/events' with parameters: " +
                "text {}, categories {}, paid {}, rangeStart {}, rangeEnd {}, " +
                "onlyAvailable {}, sort {}, from {}, size {}",
                text, categories, paid, rangeStart, rangeEnd,
                onlyAvailable, sort, from, size);
        List<EventShortDto> events = eventService.getPublicEvents(
                text, categories, paid,
                stringToDateTime(rangeStart),
                stringToDateTime(rangeEnd),
                onlyAvailable, sort, from, size,
                request.getRemoteAddr(), request.getRequestURI());
        sendStats(request.getRequestURI(), request.getRemoteAddr());
        return events;
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public EventFullDto getEvent(
            @PathVariable Long id,
            HttpServletRequest request
    ) {
        log.info("GET-request '/events/{}'", id);
        EventFullDto event = eventService.getEvent(id, request.getRemoteAddr(), request.getRequestURI());
        sendStats(request.getRequestURI(), request.getRemoteAddr());
        return event;
    }

    private void sendStats(String uri, String ip) {
        try {
            EndpointHit dto = new EndpointHit();
            dto.setApp("ewm-main-service");
            dto.setIp(ip);
            dto.setUri(uri);
            dto.setTimestamp(LocalDateTime.now());
            ResponseEntity<Object> result = httpClient.addHit(dto);
            log.info("Информация о запросе сохранена на сервере статистики: " + result);
        } catch (Exception e) {
            log.info("Не удалось сохранить информацию на сервере статистики. Ошибка: " + e.getMessage());
        }

    }

}
