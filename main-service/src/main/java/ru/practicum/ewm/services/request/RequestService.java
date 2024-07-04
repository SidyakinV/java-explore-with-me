package ru.practicum.ewm.services.request;

import org.springframework.stereotype.Service;
import ru.practicum.ewm.dto.request.ParticipationRequestDto;

import java.util.List;

@Service
public interface RequestService {

    List<ParticipationRequestDto> getUserRequests(Long userId);

    ParticipationRequestDto addEventRequest(Long userId, Long eventId);

    ParticipationRequestDto cancelEventRequest(Long userId, Long requestId);

}
