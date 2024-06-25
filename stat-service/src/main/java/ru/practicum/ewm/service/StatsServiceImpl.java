package ru.practicum.ewm.service;

import dto.EndpointHit;
import dto.ViewStats;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.model.StatsMapper;
import ru.practicum.ewm.storage.StatsRepository;
import ru.practicum.ewm.model.Stats;
import ru.practicum.ewm.storage.StatsStorage;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatsServiceImpl implements StatsService {

    private final StatsRepository statsRepository;
    private final StatsStorage statsStorage;

    @Override
    public EndpointHit addHit(EndpointHit dto) {
        Stats stat = statsRepository.save(StatsMapper.mapHitDtoToStats(dto));
        log.info("Hit was added: {}", stat);
        return StatsMapper.mapStatsToHitDto(stat);
    }

    @Override
    public List<ViewStats> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique) {
        List<ViewStats> list = statsStorage.getStats(start, end, uris, unique);
        log.info("Получено записей: {}", list.size());
        return list;
    }

}
