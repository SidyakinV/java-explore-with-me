package ru.practicum.ewm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.ewm.dto.event.ICalcRating;
import ru.practicum.ewm.models.EventRating;

public interface EventRatingRepository extends JpaRepository<EventRating, Long> {

    EventRating findByEventIdAndParticipantId(Long eventId, Long participantId);

    @Query("SELECT COUNT(*) AS ratesCount, SUM(rate) as ratesTotal " +
            "FROM EventRating " +
            "WHERE eventId = :eventId")
    ICalcRating calcEventRating(Long eventId);

    @Query(value = "SELECT COUNT(*) AS ratesCount, SUM(r.rate) as ratesTotal " +
            "FROM events_ratings AS r " +
            "  JOIN events AS e ON r.event_id = e.id " +
            "WHERE e.initiator_id = :initiatorId",
            nativeQuery = true)
    ICalcRating calcInitiatorRating(Long initiatorId);

}
