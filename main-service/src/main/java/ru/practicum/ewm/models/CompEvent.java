package ru.practicum.ewm.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "compilation_events")
@Data
public class CompEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "compilation_id", nullable = false)
    private Long compilationId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id")
    private Event event;

}
