package ru.practicum.ewm.models;

import lombok.Data;
import ru.practicum.ewm.enums.request.RequestStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "requests")
@Data
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "requester_id")
    private User requester;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @Column(name = "status", nullable = false)
    private RequestStatus status;

}
