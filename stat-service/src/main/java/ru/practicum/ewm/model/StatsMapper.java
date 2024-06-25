package ru.practicum.ewm.model;

import dto.EndpointHit;

public class StatsMapper {

    public static Stats mapHitDtoToStats(EndpointHit hit) {
        Stats entity = new Stats();
        entity.setApp(hit.getApp());
        entity.setIp(hit.getIp());
        entity.setUri(hit.getUri());
        entity.setTimestamp(hit.getTimestamp());
        return entity;
    }

    public static EndpointHit mapStatsToHitDto(Stats entity) {
        EndpointHit hit = new EndpointHit();
        hit.setId(entity.getId());
        hit.setApp(entity.getApp());
        hit.setIp(entity.getIp());
        hit.setUri(entity.getUri());
        hit.setTimestamp(entity.getTimestamp());
        return hit;
    }

}
