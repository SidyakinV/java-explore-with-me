package ru.practicum.ewm.service;

import dto.EndpointHit;
import dto.ViewStats;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsService {

    EndpointHit addHit(EndpointHit dto);

    List<ViewStats> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique);

}
