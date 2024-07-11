package ru.practicum.ewm.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "events_ratings")
@Data
public class EventRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_id", nullable = false)
    private Long eventId;

    @Column(name = "participant_id", nullable = false)
    private Long participantId;

    @Column(name = "rate", nullable = false)
    private Integer rate;

}
