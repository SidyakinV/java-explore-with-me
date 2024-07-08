package ru.practicum.ewm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm.models.LogIp;

public interface LogIpRepository extends JpaRepository<LogIp, Long> {

    LogIp findByIpAndPath(String ip, String path);

}
