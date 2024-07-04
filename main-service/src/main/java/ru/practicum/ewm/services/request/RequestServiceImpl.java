package ru.practicum.ewm.services.request;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.dto.request.ParticipationRequestDto;
import ru.practicum.ewm.enums.event.EventState;
import ru.practicum.ewm.enums.request.RequestStatus;
import ru.practicum.ewm.exceptions.ConflictException;
import ru.practicum.ewm.exceptions.NotFoundException;
import ru.practicum.ewm.mappers.RequestMapper;
import ru.practicum.ewm.models.Event;
import ru.practicum.ewm.models.Request;
import ru.practicum.ewm.models.User;
import ru.practicum.ewm.repositories.EventRepository;
import ru.practicum.ewm.repositories.RequestRepository;
import ru.practicum.ewm.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Override
    public List<ParticipationRequestDto> getUserRequests(Long userId) {
        User requester = findUserById(userId);

        List<Request> requests = requestRepository.findByRequester(requester);
        log.info("Получен список запросов на участие пользователя с id={}", userId);

        return requests.stream()
                .map(RequestMapper::mapRequestToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ParticipationRequestDto addEventRequest(Long userId, Long eventId) {
        User requester = findUserById(userId);
        Event event = findEventById(eventId);

        Request request = requestRepository.findByEventAndRequester(event, requester);
        if (request != null) {
            throw new ConflictException(String.format(
                    "Пользователь с userId=%d уже подавал заявку на участие в событии с eventId=%d",
                    userId, eventId));
        }

        if (Objects.equals(event.getInitiator().getId(), userId)) {
            throw new ConflictException("Инициатор события не может добавить запрос на участие в своём событии");
        }

        if (!event.getState().equals(EventState.PUBLISHED)) {
            throw new ConflictException("Нельзя подать заявку на участие, т.к. событие еще не опубликовано");
        }

        if (event.getParticipantLimit() > 0) {
            long confirmed = requestRepository.countByEventAndStatus(event, RequestStatus.CONFIRMED);
            if (confirmed >= event.getParticipantLimit()) {
                throw new ConflictException("Достигнут лимит заявок на участие в событии");
            }
        }

        request = new Request();
        request.setRequester(requester);
        request.setEvent(event);
        request.setCreated(LocalDateTime.now());
        request.setStatus(event.getRequestModeration() ? RequestStatus.PENDING : RequestStatus.CONFIRMED);

        Request savedRequest = requestRepository.save(request);
        log.info("Добавлен новый запрос на участие: {}", savedRequest);

        return RequestMapper.mapRequestToDto(savedRequest);
    }

    @Override
    public ParticipationRequestDto cancelEventRequest(Long userId, Long requestId) {
        findUserById(userId);
        Request request = findRequestById(requestId);

        request.setStatus(RequestStatus.REJECTED);

        Request savedRequest = requestRepository.save(request);
        log.info("Заявка на участие отмена пользователем: {}", savedRequest);

        return RequestMapper.mapRequestToDto(savedRequest);
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException(String.format(
                        "User with id=%d was not found", userId)));
    }

    private Event findEventById(Long eventId) {
        return eventRepository.findById(eventId).orElseThrow(() ->
                new NotFoundException(String.format(
                        "Event with id=%d was not found", eventId)));
    }

    private Request findRequestById(Long requestId) {
        return requestRepository.findById(requestId).orElseThrow(() ->
                new NotFoundException(String.format("Заявка с id=%d не найдена", requestId)));
    }

}
