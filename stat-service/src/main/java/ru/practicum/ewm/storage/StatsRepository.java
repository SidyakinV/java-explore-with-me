package ru.practicum.ewm.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm.model.Stats;

public interface StatsRepository extends JpaRepository<Stats, Long> { }
