package ru.practicum.ewm.mappers;

import ru.practicum.ewm.dto.request.ParticipationRequestDto;
import ru.practicum.ewm.models.Request;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RequestMapper {

    public static ParticipationRequestDto mapRequestToDto(Request request) {
        ParticipationRequestDto dto = new ParticipationRequestDto();
        dto.setId(request.getId());
        dto.setEvent(request.getEvent().getId());
        dto.setRequester(request.getRequester().getId());
        dto.setCreated(request.getCreated().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        dto.setStatus(request.getStatus().toString());
        return dto;
    }

    public static List<ParticipationRequestDto> mapRequestsToDtoList(List<Request> requests) {
        return requests.stream()
                    .map(RequestMapper::mapRequestToDto)
                    .collect(Collectors.toList());
    }

}
