package ru.practicum.ewm.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm.models.Event;
import ru.practicum.ewm.models.User;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByInitiator(User initiator, Pageable pageable);

    Event findByIdAndInitiator(Long eventId, User initiator);

}
