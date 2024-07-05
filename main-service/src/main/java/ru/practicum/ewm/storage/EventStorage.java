package ru.practicum.ewm.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EventStorage {

    private final JdbcTemplate jdbcTemplate;



}
