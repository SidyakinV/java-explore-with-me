package ru.practicum.ewm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.ewm.enums.request.RequestStatus;
import ru.practicum.ewm.models.Event;
import ru.practicum.ewm.models.Request;
import ru.practicum.ewm.models.User;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findByEvent(Event event);

    List<Request> findByEventAndStatus(Event event, RequestStatus status);

    List<Request> findByRequester(User requester);

    Request findByEventAndRequester(Event event, User requester);

    @Query(value = "UPDATE Request SET status = :status WHERE id IN (:ids)")
    void updateStatusByIds(RequestStatus status, List<Long> ids);

}
