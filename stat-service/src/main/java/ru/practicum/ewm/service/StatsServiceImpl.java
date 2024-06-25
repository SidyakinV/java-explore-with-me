package ru.practicum.ewm.service;

import dto.EndpointHit;
import dto.IVewStats;
import dto.ViewStats;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.model.StatsMapper;
import ru.practicum.ewm.storage.StatsRepository;
import ru.practicum.ewm.model.Stats;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatsServiceImpl implements StatsService {

    private final StatsRepository statsRepository;

    @Override
    public EndpointHit addHit(EndpointHit dto) {
        Stats stat = statsRepository.save(StatsMapper.mapHitDtoToStats(dto));
        log.info("Hit was added: {}", stat);
        return StatsMapper.mapStatsToHitDto(stat);
    }

    @Override
    public List<ViewStats> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique) {
        List<IVewStats> list;

        if (uris == null || uris.isEmpty()) {
            if (unique) {
                list = statsRepository.getUniqueStatsByPer(start, end);
            } else {
                list = statsRepository.getAllStatsByPer(start, end);
            }
        } else {
            if (unique) {
                list = statsRepository.getUniqueStatsByPerAndUris(start, end, uris);
            } else {
                list = statsRepository.getAllStatsByPerAndUris(start, end, uris);
            }
        }

        return list.stream()
                .map(this::mapObjToStat)
                .collect(Collectors.toList());
    }

    private ViewStats mapObjToStat(IVewStats obj) {
        return new ViewStats(obj.getApp(), obj.getUri(), obj.getHits());
    }

}
