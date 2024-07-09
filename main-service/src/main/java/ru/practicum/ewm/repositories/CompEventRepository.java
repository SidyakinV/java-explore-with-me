package ru.practicum.ewm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.models.CompEvent;

import java.util.List;

public interface CompEventRepository extends JpaRepository<CompEvent, Long> {

    @Modifying
    @Transactional
    @Query(value =
            "INSERT INTO compilation_events (compilation_id, event_id) " +
                    "VALUES (:compId, :eventId)",
            nativeQuery = true)
    void insertCompEvent(long compId, long eventId);

    @Modifying
    @Transactional
    @Query(value =
            "DELETE FROM compilation_events WHERE compilation_id = :compId",
            nativeQuery = true)
    void deleteCompEvents(long compId);

    List<CompEvent> findAllByCompilationId(Long compilationId);

}
