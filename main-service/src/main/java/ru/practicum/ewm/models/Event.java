package ru.practicum.ewm.models;

import lombok.Data;
import ru.practicum.ewm.enums.event.EventState;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cat_id")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "initiator_id")
    private User initiator;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String annotation;

    @Column(nullable = false)
    private String description;

    @Column(name = "event_date", nullable = false)
    private LocalDateTime eventDate;

    @Column(name = "location_lat", nullable = false)
    private Float locationLat;

    @Column(name = "location_lon", nullable = false)
    private Float locationLon;

    @Column(nullable = false)
    private Boolean paid;

    @Column(name = "participant_limit", nullable = false)
    private Integer participantLimit;

    @Column(name = "request_moderation", nullable = false)
    private Boolean requestModeration;

    @Column(name = "confirmed_requests", nullable = false)
    private Long confirmedRequests;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column(nullable = true)
    private LocalDateTime published;

    @Column(nullable = false)
    private Long views;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventState state;

    private Long rating;

}
