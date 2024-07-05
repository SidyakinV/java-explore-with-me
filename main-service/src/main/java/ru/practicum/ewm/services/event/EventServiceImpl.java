package ru.practicum.ewm.services.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.dto.event.*;
import ru.practicum.ewm.dto.request.EventRequestStatusUpdateRequest;
import ru.practicum.ewm.dto.request.EventRequestStatusUpdateResult;
import ru.practicum.ewm.dto.request.ParticipationRequestDto;
import ru.practicum.ewm.enums.event.EventActionState;
import ru.practicum.ewm.enums.event.EventSort;
import ru.practicum.ewm.enums.event.EventState;
import ru.practicum.ewm.enums.request.RequestStatus;
import ru.practicum.ewm.exceptions.ConflictException;
import ru.practicum.ewm.exceptions.NotFoundException;
import ru.practicum.ewm.exceptions.UnsupportedException;
import ru.practicum.ewm.exceptions.ValidationException;
import ru.practicum.ewm.mappers.EventMapper;
import ru.practicum.ewm.mappers.RequestMapper;
import ru.practicum.ewm.models.Category;
import ru.practicum.ewm.models.Event;
import ru.practicum.ewm.models.Request;
import ru.practicum.ewm.models.User;
import ru.practicum.ewm.repositories.CategoryRepository;
import ru.practicum.ewm.repositories.EventRepository;
import ru.practicum.ewm.repositories.RequestRepository;
import ru.practicum.ewm.repositories.UserRepository;
import ru.practicum.ewm.utility.PageCalc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.data.domain.Sort.sort;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final RequestRepository requestRepository;

    @Override
    public EventFullDto addEvent(Long userId, NewEventDto dto) {
        User initiator = findUserById(userId);
        Category category = findCategoryById(dto.getCategory());

        Event event = EventMapper.mapNewEventDtoToEvent(dto);
        event.setCategory(category);
        event.setInitiator(initiator);
        event.setConfirmedRequests(0L);
        event.setState(EventState.PENDING);
        event.setViews(0L);

        checkEventDate(event.getEventDate(), 2L);

        Event savedEvent = eventRepository.save(event);
        log.info("Добавлено новое событие: {}", savedEvent);

        return EventMapper.mapEventToEventFullDto(event);
    }

    @Override
    public List<EventShortDto> getUserEvents(Long userId, Pageable pageable) {
        User user = findUserById(userId);

        List<Event> events = eventRepository.findByInitiator(user, pageable);
        log.info("Получен список событий пользователя с id={}", userId);

        return events.stream()
                .map(EventMapper::mapEventToEventShortDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventFullDto getEvent(Long eventId) {
        Event event = findEventById(eventId);
        log.info("Получено событие с id={}", event);
        return EventMapper.mapEventToEventFullDto(event);
    }

    @Override
    public EventFullDto getEvent(Long userId, Long eventId) {
        User user = findUserById(userId);

        Event event = eventRepository.findByIdAndInitiator(eventId, user);
        if (event == null) {
            throw new NotFoundException(String.format(
                    "Event with id=%d and userId=%d was not found", eventId, userId));
        }

        log.info("Получено событие с id={} для пользователя с userId={}", event, userId);
        return EventMapper.mapEventToEventFullDto(event);
    }

    @Override
    public EventFullDto updateEvent(Long eventId, UpdateEventAdminRequest dto) {
        Event oldEvent = findEventById(eventId);
        Event newEvent = EventMapper.mapUpdateEventAdminDtoToEvent(dto, oldEvent);

        checkEventDate(oldEvent.getEventDate(), 1L);

        newEvent.setInitiator(oldEvent.getInitiator());
        if (dto.getCategory() != null) {
            newEvent.setCategory(findCategoryById(dto.getCategory()));
        } else {
            newEvent.setCategory(oldEvent.getCategory());
        }

        if (dto.getStateAction() != null) {
            switch (EventActionState.stringToEventActionState(dto.getStateAction())) {
                case PUBLISH_EVENT:
                    if (!oldEvent.getState().equals(EventState.PENDING)) {
                        throw new ConflictException(String.format(
                                "Cannot publish the event because it's not in the right state: %s", oldEvent.getState()));
                    }
                    newEvent.setState(EventState.PUBLISHED);
                    break;
                case REJECT_EVENT:
                    if (!oldEvent.getState().equals(EventState.PUBLISHED)) {
                        throw new ConflictException(String.format(
                                "Cannot publish the event because it's not in the right state: %s", oldEvent.getState()));
                    }
                    newEvent.setState(EventState.CANCELED);
                    break;
            }
        }

        Event savedEvent = eventRepository.save(newEvent);
        log.info("Событие изменено: {}", savedEvent);

        return EventMapper.mapEventToEventFullDto(savedEvent);
    }

    @Override
    public EventFullDto updateEvent(Long userId, Long eventId, UpdateEventUserRequest dto) {
        findUserById(userId);
        Event oldEvent = findEventById(eventId);

        if (!Objects.equals(oldEvent.getInitiator().getId(), userId)) {
            throw new ConflictException("Событие оформлено другим пользователем");
        }

        Event newEvent = EventMapper.mapUpdateUserEventDtoToEvent(dto);

        checkEventDate(newEvent.getEventDate(), 2L);
        checkEventState(newEvent.getState());

        newEvent.setCategory(findCategoryById(dto.getCategory()));
        newEvent.setState(oldEvent.getState());

        EventActionState action = EventActionState.stringToEventActionState(dto.getStateAction());
        if (action == EventActionState.REJECT_EVENT && oldEvent.getState().equals(EventState.PENDING)) {
            newEvent.setState(EventState.CANCELED);
        }

        Event savedEvent = eventRepository.save(newEvent);
        log.info("Событие изменено: {}", savedEvent);

        return EventMapper.mapEventToEventFullDto(savedEvent);
    }

    @Override
    public List<ParticipationRequestDto> getEventRequests(Long userId, Long eventId) {
        findUserById(userId);
        Event event = findEventById(eventId);

        List<Request> requests = requestRepository.findByEvent(event);
        return requests.stream()
                .map(RequestMapper::mapRequestToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventRequestStatusUpdateResult changeEventRequestsStatus(
            Long userId, Long eventId, EventRequestStatusUpdateRequest dto
    ) {
        findUserById(userId);
        Event event = findEventById(eventId);

        EventRequestStatusUpdateResult result = new EventRequestStatusUpdateResult();

        if (event.getParticipantLimit() > 0 && event.getRequestModeration()) {
            switch (RequestStatus.stringToRequestStatus(dto.getStatus())) {
                case REJECTED:
                    result.getRejectedRequests().addAll(
                            updateRequestStatusByIds(eventId, dto.getRequestIds(), RequestStatus.REJECTED));
                    break;
                case CONFIRMED:
                    int available = (int) (event.getParticipantLimit() - event.getConfirmedRequests());
                    if (available < dto.getRequestIds().size()) {
                        throw new ConflictException("The request limit has been exceeded");
                    }
                    result.getConfirmedRequests().addAll(
                            updateRequestStatusByIds(eventId, dto.getRequestIds(), RequestStatus.CONFIRMED));
                    if (available == dto.getRequestIds().size()) {
                        result.getRejectedRequests().addAll(rejectEventRequests(event));
                    }
                    event.setConfirmedRequests(event.getConfirmedRequests() + dto.getRequestIds().size());
                    break;
                default:
                    throw new UnsupportedException("New request status must be 'CONFIRMED' or 'REJECTED'");
            }
        }

        return result;
    }

    @Override
    public List<EventFullDto> getAdminEvents(
            List<Long> userList, List<String> stateList, List<Long> categoryList,
            LocalDateTime rangeStart, LocalDateTime rangeEnd, Pageable pageable
    ) {
        List<Long> users = (userList == null || userList.isEmpty()) ? null : userList;
        List<Long> categories = (categoryList == null || categoryList.isEmpty()) ? null : categoryList;

        List<EventState> states = null;
        if (stateList != null && !stateList.isEmpty()) {
            states = stateList.stream()
                    .map(EventState::stringToEventState)
                    .collect(Collectors.toList());
        }

        /*
        List<Event> events = eventRepository.getEventsList(
                null,
                //rangeStart, rangeEnd, null, null,
                //states, users, categories,
                pageable).getContent();

        return events.stream()
                .map(EventMapper::mapEventToEventFullDto)
                .collect(Collectors.toList());

         */
        throw new RuntimeException("sdafsdf");
    }

    @Override
    public List<EventShortDto> getPublicEvents(
            String searchText, List<Long> categoryList, Boolean paid,
            LocalDateTime rangeStart, LocalDateTime rangeEnd,
            Boolean onlyAvailable, String sortBy, Integer from, Integer size
    ) {
        String text = searchText.isBlank() ? null : searchText;
        List<Long> categories = (categoryList == null || categoryList.isEmpty()) ? null : categoryList;

        List<EventState> states = new ArrayList<>();
        states.add(EventState.PUBLISHED);

        Sort sort = Sort.unsorted();
        if (sortBy != null) {
            switch (EventSort.stringToEventSort(sortBy)) {
                case EVENT_DATE:
                    sort = Sort.by("e.eventDate");
                    break;
                case VIEWS:
                    sort = Sort.by("e.views").descending();
                    break;
            }
        }

        Pageable pageable = PageCalc.getPageable(from, size, sort);

        List<Event> events = eventRepository.getEventsList(
                text, rangeStart, rangeEnd,
                paid, onlyAvailable,
                states, null, categories,
                pageable).getContent();


        return events.stream()
                .map(EventMapper::mapEventToEventShortDto)
                .collect(Collectors.toList());
    }

    private Category findCategoryById(Long catId) {
        return categoryRepository.findById(catId).orElseThrow(() ->
                new NotFoundException(String.format("Category with id=%d was not found", catId)));
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException(String.format(
                        "User with id=%d was not found", userId)));
    }

    private void checkEventDate(LocalDateTime eventDate, Long hours) {
        if (LocalDateTime.now().isAfter(eventDate.minusHours(hours))) {
            throw new ConflictException(String.format(
                    "Field: eventDate. Error: должно содержать дату, которая еще не наступила. Value: %s",
                    eventDate));
        }
    }

    private void checkEventState(EventState state) {
        if (state.equals(EventState.PUBLISHED)) {
            throw new ConflictException("Event must not be published");
        }
    }

    private Event findEventById(Long eventId) {
        return eventRepository.findById(eventId).orElseThrow(() ->
                new NotFoundException(String.format(
                        "Event with id=%d was not found", eventId)));
    }

    private List<ParticipationRequestDto> updateRequestStatusByIds(long eventId, List<Long> ids, RequestStatus status) {
        List<Request> requests = requestRepository.findAllById(ids);

        boolean isOk = requests.stream()
                .filter(request ->
                        !(request.getStatus().equals(RequestStatus.PENDING) && request.getEvent().getId() == eventId))
                .findFirst()
                .isEmpty();
        if (!isOk) {
            throw new ValidationException("Request must have status PENDING");
        }

        requestRepository.updateStatusByIds(status, ids);

        return RequestMapper.mapRequestsToDtoList(requests);
    }

    private List<ParticipationRequestDto> rejectEventRequests(Event event) {
        List<Request> requests = requestRepository.findByEventAndStatus(event, RequestStatus.PENDING);

        List<Long> ids = requests.stream()
                .map(Request::getId)
                .collect(Collectors.toList());

        requestRepository.updateStatusByIds(RequestStatus.REJECTED, ids);

        return RequestMapper.mapRequestsToDtoList(requests);
    }

}
