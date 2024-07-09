package ru.practicum.ewm.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "log_ip")
@Data
public class LogIp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ip", nullable = false)
    private String ip;

    @Column(name = "path", nullable = false)
    private String path;

    @Column(name = "first_time", nullable = false)
    private LocalDateTime first;

    @Column(name = "last_time", nullable = false)
    private LocalDateTime last;

    @Column(name = "view_count", nullable = false)
    private Long count;

}
