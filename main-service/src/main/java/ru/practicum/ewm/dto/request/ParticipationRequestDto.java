package ru.practicum.ewm.dto.request;

import lombok.Data;

@Data
public class ParticipationRequestDto {

    private String created; // Используем тип String, как указано в спецификации

    private Long event;

    private Long id;

    private Long requester;

    private String status;

}
