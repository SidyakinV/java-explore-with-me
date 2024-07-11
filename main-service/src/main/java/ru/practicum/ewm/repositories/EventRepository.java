package ru.practicum.ewm.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.ewm.enums.event.EventState;
import ru.practicum.ewm.models.Event;
import ru.practicum.ewm.models.User;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByInitiator(User initiator, Pageable pageable);

    Event findByIdAndInitiator(Long eventId, User initiator);

    @Query("SELECT e " +
            "FROM Event as e " +
            "  JOIN e.category " +
            "  JOIN e.initiator " +
            "WHERE (:text IS NULL " +
            "          OR LOWER(e.annotation) LIKE LOWER(CONCAT('%', :text, '%')) " +
            "          OR LOWER(e.description) LIKE LOWER(CONCAT('%', :text, '%'))) " +
            "  AND (CAST(:start AS timestamp) IS NULL OR e.eventDate >= :start) " +
            "  AND (CAST(:end AS timestamp) IS NULL OR e.eventDate <= :end) " +
            "  AND (:paid IS NULL OR e.paid = :paid) " +
            "  AND (:onlyAvailable IS NULL OR e.confirmedRequests < e.participantLimit) " +
            "  AND (:states IS NULL OR e.state IN (:states)) " +
            "  AND (:users IS NULL OR e.initiator.id IN (:users)) " +
            "  AND (:categories IS NULL OR e.category.id IN (:categories)) "
    )
    Slice<Event> getEventsList(
            String text,
            LocalDateTime start, LocalDateTime end,
            Boolean paid, Boolean onlyAvailable,
            List<EventState> states, List<Long> users, List<Long> categories,
            Pageable pageable);

    @Query("SELECT count(*) > 0 " +
            "FROM Event as e " +
            "  JOIN e.category " +
            "WHERE e.category.id = :catId")
    boolean existsByCatId(Long catId);

}
